package com.doit.study.mapper;

public class BoardSQL {
    // oracle
//    public static final String count =
//            "SELECT COUNT (*) FROM BO_STUDY_TB";
    // mysql
    public static final String count =
            "SELECT COUNT(*) FROM BO_STUDY_TB";

    public static final String deleteAll =
            "DELETE FROM BO_STUDY_TB";

    public static final String delete =
            "DELETE FROM BO_STUDY_TB WHERE board_Id = #{board_Id}";

    // oracle
//    public static final String insert =
//            "insert into BO_STUDY_TB (board_Id, board_Title, board_SubTitle, board_Content, board_Writer) " +
//            "values (board_id_seq.NEXTVAL, #{board_Title}, #{board_SubTitle}, #{board_Content}, #{board_Writer})";
    // mysql
    public static final String insert =
            "insert into BO_STUDY_TB (board_Title, board_SubTitle, board_Content, board_Writer) " +
            "values (#{board_Title}, #{board_SubTitle}, #{board_Content}, #{board_Writer})";

    public static final String selectAll =
            "SELECT * " +
            "FROM BO_STUDY_TB " +
            "ORDER BY BOARD_ID DESC";

    public static final String select =
            "SELECT * " +
            "FROM BO_STUDY_TB " +
            "WHERE board_Id = #{board_Id}";
    // oracle
//    public static final String selectPage =
//        "SELECT board_Id, board_Title, board_Writer, board_SubTitle, board_Count, board_Comment, board_RegDate\n" +
//                "FROM (\n" +
//                "         SELECT rownum rnum, A.*\n" +
//                "         from (\n" +
//                "                  select board_Id, board_Title, board_Writer, board_SubTitle, board_Count, board_Comment, board_RegDate\n" +
//                "                  from BO_STUDY_TB\n" +
//                "                  order by board_Id desc\n" +
//                "              ) A\n" +
//                "     )\n" +
//                "where rnum > ${firstRecordIndex} AND rnum <= ${lastRecordIndex}";
    // mysql
    public static final String selectPage =
        "SELECT *\n" +
                " FROM bo_study_tb\n" +
                " order by BOARD_ID desc\n" +
                " LIMIT #{offset}, #{countPerPage}";

//    public static final String update =
//            "UPDATE BO_STUDY_TB " +
//            "SET board_Title = #{board_Title}" +
//            ", board_Content = #{board_Content} " +
//            "WHERE board_Writer = #{board_Writer} ";

    public static final String update =
            "UPDATE BO_STUDY_TB " +
                    "SET board_Title = #{board_Title}" +
                    ", board_Content = #{board_Content} " +
                    "WHERE board_Writer = #{board_Writer} " +
                    "and board_Id= #{board_Id}";

    public static final String increaseViewCount =
            "UPDATE BO_STUDY_TB " +
            "SET board_Count = board_Count + 1 " +
            "WHERE board_Id = #{board_Id}";
    // oracle
//    public static final String searchSelectPage =
//    "SELECT board_Id, board_Title, board_Writer, board_SubTitle, board_Count, board_Comment, board_date\n" +
//                    "FROM (\n" +
//                    "SELECT rownum rnum, A.*\n" +
//                    " from (\n" +
//                    "  select board_Id, board_Title, board_Writer, board_SubTitle, board_Count, board_Comment, board_date\n" +
//                    "  from BO_STUDY_TB\n" +
//                    "  where board_Title like'%' || #{board_Title} || '%'\n" +
//                    " order by board_Id desc\n" +
//                    "  ) A\n" +
//                    " )\n" +
//                    " where rnum > ${firstRecordIndex} AND rnum <= ${lastRecordIndex}";


    // mysql
    public static final String searchSelectPage =
    "SELECT *\n" +
            "FROM bo_study_tb\n" +
            "where board_Title like CONCAT('%',#{board_Title},'%')\n" +
            "order by BOARD_ID desc\n" +
            " LIMIT #{offset}, #{countPerPage}";
    // oracle
//    public static final String searchResultCount =
//    "select COUNT (*) from BO_STUDY_TB\n" +
//            "                    where board_Title like'%' || #{board_Title} || '%'";
    // mysql
    public static final String searchResultCount =
    "SELECT COUNT(*) from BO_STUDY_TB\n" +
            "   where board_Title like CONCAT('%',#{board_Title},'%')";

    public static final String updateCommentCount =
            "UPDATE BO_STUDY_TB " +
                    "SET board_Comment = board_Comment + #{count} " +
                    "where board_Id = #{board_Id}";

}
