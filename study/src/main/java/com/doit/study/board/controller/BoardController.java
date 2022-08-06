package com.doit.study.board.controller;

import com.doit.study.board.domain.Pagination;
import com.doit.study.board.dto.BoardDto;
import com.doit.study.board.service.BoardService;
import com.doit.study.comment.service.CommentService;
import com.doit.study.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize,
                       Model model, HttpServletRequest request){
        try {
            BoardDto boardDto = new BoardDto();
            int totalRecordCount = boardService.getCount();
            Pagination pagination = new Pagination(currentPage, pageSize);
            pagination.setTotalRecordCount(totalRecordCount);

            model.addAttribute("pagination", pagination);
            model.addAttribute("list", boardService.getPage(pagination));
            log.info("list = " + boardService.getPage(pagination));
            model.addAttribute("board", boardDto);
            return "board/boardList";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/searchList")
    public String searchList(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize,
                             BoardDto boardDto, ServletRequest request, Model m)
            throws Exception {
        int totalRecordCount = boardService.searchResultCount(boardDto);
        Pagination pagination = new Pagination(currentPage, pageSize);
        pagination.setTotalRecordCount(totalRecordCount);
        boardDto.doPaging(totalRecordCount);
        log.info("totalRecordCount =" + totalRecordCount );
        log.info("searchBoardDto ="+ boardDto);
        m.addAttribute("searchList", boardService.searchSelectPage(boardDto));
        return "board/searchBoardList";
    }

    @GetMapping("/read")
    public String read(Integer board_Id, Model m, HttpSession session, BoardDto boardDto){
        String board_Writer = (String)session.getAttribute("nickName");
        boardDto.setBoard_Writer(board_Writer);
        log.info("board_Writer = "+board_Writer);
        try {
            boardDto = boardService.read(board_Id);
            m.addAttribute("board", boardDto);
            m.addAttribute("board_Writer", board_Writer);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/board/list";
        }
        return "board/board";
    }

    @GetMapping("/write")
    public String write(BoardDto boardDto) {
        return "board/insert";
    }

//    @PostMapping("/write")
//    public String write(BoardDto boardDto, MemberDto memberDto, HttpSession session, HttpServletRequest request, Model m, BindingResult bindingResult) throws Exception {
//        String board_Writer = (String)session.getAttribute("nickName");
//        boardDto.setBoard_Writer(board_Writer);
//        log.info("board_Writer = "+board_Writer);
//        boardService.write(boardDto);
//        m.addAttribute("board_Writer", board_Writer);
//        return "redirect:/board/list";
//    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute("boardDto") BoardDto boardDto, HttpSession session, HttpServletRequest request, Model m,
                        BindingResult bindingResult) throws Exception {
        String board_Writer = (String)session.getAttribute("nickName");
        boardDto.setBoard_Writer(board_Writer);
        if(bindingResult.hasErrors()) {
            log.info("타입 오류 발생, error={}", bindingResult);
            return "board/insert";
        }
        String board_Title = request.getParameter("board_Title");
        String board_Content = request.getParameter("board_Content");
        if(board_Title==null){
            bindingResult.reject("writeFail", "제목을 입력해주세요.");
            return "board/insert";
        }
        if(board_Content==null){
            bindingResult.reject("writeFail", "내용을 입력해주세요.");
            return "board/insert";
        }
        log.info("board_Writer = "+board_Writer);
        boardService.write(boardDto);
        m.addAttribute("board_Writer", board_Writer);
        return "redirect:/board/list";
    }

//    @PostMapping("/write")
//    public String write(@Valid @ModelAttribute("boardDto") BoardDto boardDto, MemberDto memberDto, HttpSession session, HttpServletRequest request, Model m,
//                        BindingResult bindingResult, RedirectAttributes rattr){
//        String board_Writer = (String)session.getAttribute("nickName");
//        boardDto.setBoard_Writer(board_Writer);
//        String board_Title = request.getParameter("board_Title");
//        String board_Content = request.getParameter("board_Content");
//        log.info("board_Writer = "+board_Writer);
//
//
//        try {
//            if (board_Title==null)
//                throw new Exception("Write failed.");
//
//            if (board_Content==null)
//                throw new Exception("Write failed.");
//
//            boardService.write(boardDto);
//            m.addAttribute("board_Writer", board_Writer);
//            rattr.addFlashAttribute("msg", "WRT_OK");
//            return "redirect:/board/list";
//        } catch (Exception e) {
//            e.printStackTrace();
//            m.addAttribute(boardDto);
//            m.addAttribute("mode", "new");
//            m.addAttribute("msg", "WRT_ERR");
//            return "board/insert";
//        }
//
//    }

    @GetMapping("/modify")
    public String modify(Integer board_Id, Model m) throws Exception {
        BoardDto boardDto = boardService.before(board_Id);
        m.addAttribute("boardDto", boardDto);
        log.info("board_Id = " + board_Id);
        return "board/update";
    }

    @PostMapping("/modify")
    public String modify(HttpSession session, @Valid @ModelAttribute("boardDto") BoardDto boardDto, HttpServletRequest request) throws Exception {
        String board_Writer = (String)session.getAttribute("nickName");
        String board_Id = (String) request.getParameter("board_Id");
        boardDto.setBoard_Id(Integer.valueOf(board_Id));
        boardDto.setBoard_Writer(board_Writer);
        boardService.modify(boardDto);
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(Integer board_Id, BoardDto boardDto) throws Exception {
        commentService.removeAll(board_Id);
        boardService.remove(boardDto);
        return "redirect:/board/list";
    }

}