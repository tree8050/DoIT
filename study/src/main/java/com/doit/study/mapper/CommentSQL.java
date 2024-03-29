package com.doit.study.mapper;

public class CommentSQL {

    public static final String deleteAll =
            "DELETE FROM BO_COMMENT_TB\n" +
            "WHERE  board_Id = #{board_Id}";

    public static final String count =
            "SELECT count(*) FROM BO_COMMENT_TB\n" +
            "WHERE  board_Id = #{board_Id}";

    public static final String delete =
            "DELETE FROM BO_COMMENT_TB " +
            "WHERE comment_Id = #{comment_Id} AND comment_Writer = #{comment_Writer}";

    public static final String delete2 =
            "UPDATE BO_COMMENT_TB\n" +
            "SET comment_Content = #{comment_Content}\n" +
            ", update_Date = now()\n" +
            "WHERE comment_Id = #{comment_Id} and comment_Writer = #{comment_Writer}";

    // oracle
//    public static final String insert =
//            "INSERT INTO BO_COMMENT2_TB\n" +
//            "(comment_Id, parentComment_Id, board_Id, comment_Content, comment_Writer, reg_Date, update_Date)\n" +
//            "VALUES\n" +
//            "(comment_Id_seq.NEXTVAL, #{parentComment_Id}, #{board_Id}, #{comment_Content}, #{comment_Writer}, sysdate, sysdate)";
    // mysql
    public static final String insert =
            "INSERT INTO BO_COMMENT_TB\n" +
                    "(parentComment_Id, board_Id, comment_Content, comment_Writer, reg_Date, update_Date)\n" +
                    "VALUES\n" +
                    "(#{parentComment_Id}, #{board_Id}, #{comment_Content}, #{comment_Writer}, now(), now())";

    public static final String selectAll =
            "SELECT *\n" +
            "FROM BO_COMMENT_TB\n" +
            "WHERE board_Id = #{board_Id}\n" +
            "ORDER BY reg_Date ASC, comment_Id ASC, parentComment_Id ASC";

    public static final String select =
            "SELECT *\n" +
            "FROM BO_COMMENT_TB\n" +
            "WHERE comment_Id = #{comment_Id}";

    public static final String update =
            "UPDATE BO_COMMENT_TB\n" +
            "SET comment_Content = #{comment_Content}\n" +
            ", update_Date = now()\n" +
            "WHERE comment_Id = #{comment_Id} and comment_Writer = #{comment_Writer}";
}
