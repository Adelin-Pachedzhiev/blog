package project.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static DataSource instance = new DataSource();
    public static final String DATABASE_NAME = "blog.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/users/adelinpachedzhiev/Documents/Udemy/Java/Blog/" + DATABASE_NAME;

    public static final String TABLE_ARTICLES = "articles";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_ARTICLE_TYPES = "article_types";

    public static final int INDEX_ARTICLE_ID = 1;
    public static final int INDEX_ARTICLE_TITLE = 2;
    public static final int INDEX_ARTICLE_CONTENT = 3;
    public static final int INDEX_ARTICLE_DATE_WRITTEN =4;
    public static final int INDEX_ARTICLE_TYPE_ID = 5;
    public static final int INDEX_ARTICLE_WRITER_ID = 6;

    public static final String COLUMN_ARTICLE_ID = "id";
    public static final String COLUMN_ARTICLE_TITLE = "title";
    public static final String COLUMN_ARTICLE_CONTENT = "content";
    public static final String COLUMN_ARTICLE_DATE_WRITTEN = "date_written";
    public static final String COLUMN_ARTICLE_TYPE_ID = "type_id";
    public static final String COLUMN_ARTICLE_WRITER_ID = "writer_id";

    public static final String COLUMN_USERS_ID = "id";
    public static final String COLUMN_USERS_NAME = "name";
    public static final String COLUMN_USERS_USERNAME = "username";
    public static final String COLUMN_USERS_PASSWORD = "password";

    public static final String COLUMN_ARTICLE_TYPES_ID = "id";
    public static final String COLUMN_ARTICLE_TYPES_NAME = "name";
    public static final String COLUMN_ARTICLE_TYPES_DESCRIPTION = "description";






    public static final String QUERY_ARTICLES_JOIN = "SELECT "+ TABLE_ARTICLES + "." + COLUMN_ARTICLE_ID + ", " +
            TABLE_USERS + "." +COLUMN_USERS_NAME + ", " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_WRITER_ID + ", "+
            TABLE_ARTICLES + "." + COLUMN_ARTICLE_TITLE + ", " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_CONTENT + ", " +
            TABLE_ARTICLES + "." + COLUMN_ARTICLE_DATE_WRITTEN + ", " + TABLE_ARTICLE_TYPES + "." + COLUMN_ARTICLE_TYPES_NAME +
            ", " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_TYPE_ID + " FROM " + TABLE_ARTICLES + " INNER JOIN " + TABLE_USERS + " ON " +
            TABLE_ARTICLES + "." + COLUMN_ARTICLE_WRITER_ID + " = " + TABLE_USERS + "." + COLUMN_USERS_ID + " INNER JOIN " +
            TABLE_ARTICLE_TYPES + " ON " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_TYPE_ID + " = " + TABLE_ARTICLE_TYPES + "." +
            COLUMN_ARTICLE_TYPES_ID;


    //SELECT articles.id, writer.name, article.writer_id, articles.title, articles.content, articles.dateWritten, article_type.name, article.type_id
    public static final String QUERY_ARTICLES =  "SELECT * FROM " + TABLE_ARTICLES;
    private Connection connection;


    public static DataSource getInstance(){
        return instance;
    }

    public void open(){
        try{
            connection = DriverManager.getConnection(CONNECTION_STRING);
        }catch (SQLException e){
            System.out.println("Problem when opening db connection " + e.getMessage());
        }
    }

    public void close(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            System.out.println("Problem when closing db" + e.getMessage());
        }

    }

    public List<Article> queryArticles(){
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ARTICLES)){
            List<Article> articles = new ArrayList<>();
            while(resultSet.next()){
                Article article = new Article();
                article.setId(resultSet.getInt(INDEX_ARTICLE_ID));
                article.setTitle(resultSet.getString(INDEX_ARTICLE_TITLE));
                article.setContent(resultSet.getString(INDEX_ARTICLE_CONTENT));
                article.setDate_written(resultSet.getString(INDEX_ARTICLE_DATE_WRITTEN));
                article.setType_id(resultSet.getInt(INDEX_ARTICLE_TYPE_ID));
                article.setWriter_id(resultSet.getInt(INDEX_ARTICLE_WRITER_ID));
                articles.add(article);
            }
            return articles;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ArticleJoin> queryArticlesJoin(){
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ARTICLES_JOIN);){
            List<ArticleJoin> articles = new ArrayList<>();
            while (resultSet.next()){
                ArticleJoin article = new ArticleJoin();
                article.setId(resultSet.getInt(1));
                article.setWriter(resultSet.getString(2));
                article.setWriter_id(resultSet.getInt(3));
                article.setTitle(resultSet.getString(4));
                article.setContent(resultSet.getString(5));
                article.setDateWritten(resultSet.getString(6));
                article.setType(resultSet.getString(7));
                article.setType_id(resultSet.getInt(8));
                articles.add(article);
            }
            return articles;
        }catch (SQLException e){
            System.out.println("Problem with database" + e.getMessage());
            return null;
        }catch(Exception e1){
            System.out.println(e1.getMessage());
            return null;
        }
    }

}
