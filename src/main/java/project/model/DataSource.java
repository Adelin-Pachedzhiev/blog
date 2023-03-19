package project.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static DataSource instance = new DataSource();
    private static final String DATABASE_NAME = "blog.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:/users/adelinpachedzhiev/Documents/Udemy/Java/Blog/" + DATABASE_NAME;

    private static final String TABLE_ARTICLES = "articles";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ARTICLE_TYPES = "article_types";

    private static final int INDEX_ARTICLE_ID = 1;
    private static final int INDEX_ARTICLE_TITLE = 2;
    private static final int INDEX_ARTICLE_CONTENT = 3;
    private static final int INDEX_ARTICLE_DATE_WRITTEN = 4;
    private static final int INDEX_ARTICLE_TYPE_ID = 5;
    private static final int INDEX_ARTICLE_WRITER_ID = 6;

    private static final String COLUMN_ARTICLE_ID = "id";
    private static final String COLUMN_ARTICLE_TITLE = "title";
    private static final String COLUMN_ARTICLE_CONTENT = "content";
    private static final String COLUMN_ARTICLE_DATE_WRITTEN = "date_written";
    private static final String COLUMN_ARTICLE_TYPE_ID = "type_id";
    private static final String COLUMN_ARTICLE_WRITER_ID = "writer_id";

    private static final String COLUMN_USERS_ID = "id";
    private static final String COLUMN_USERS_NAME = "name";
    private static final String COLUMN_USERS_USERNAME = "username";
    private static final String COLUMN_USERS_PASSWORD = "password";

    private static final String COLUMN_ARTICLE_TYPES_ID = "id";
    private static final String COLUMN_ARTICLE_TYPES_NAME = "name";
    private static final String COLUMN_ARTICLE_TYPES_DESCRIPTION = "description";


    private DataSource() {
    }

    private static final String QUERY_ARTICLES_JOIN = "SELECT " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_ID + ", " +
            TABLE_USERS + "." + COLUMN_USERS_NAME + ", " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_WRITER_ID + ", " +
            TABLE_ARTICLES + "." + COLUMN_ARTICLE_TITLE + ", " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_CONTENT + ", " +
            TABLE_ARTICLES + "." + COLUMN_ARTICLE_DATE_WRITTEN + ", " + TABLE_ARTICLE_TYPES + "." + COLUMN_ARTICLE_TYPES_NAME +
            ", " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_TYPE_ID + " FROM " + TABLE_ARTICLES + " INNER JOIN " + TABLE_USERS + " ON " +
            TABLE_ARTICLES + "." + COLUMN_ARTICLE_WRITER_ID + " = " + TABLE_USERS + "." + COLUMN_USERS_ID + " INNER JOIN " +
            TABLE_ARTICLE_TYPES + " ON " + TABLE_ARTICLES + "." + COLUMN_ARTICLE_TYPE_ID + " = " + TABLE_ARTICLE_TYPES + "." +
            COLUMN_ARTICLE_TYPES_ID;

    private static final String QUERY_USERS_USERNAME_PASSWORD = "SELECT * FROM " + TABLE_USERS + " WHERE " + TABLE_USERS +
            "." + COLUMN_USERS_USERNAME + " = ? AND " + TABLE_USERS + "." + COLUMN_USERS_PASSWORD + "= ?";

    private static final String QUERY_USERS_USERNAME = "SELECT * FROM " + TABLE_USERS + " WHERE " + TABLE_USERS + "." +
            COLUMN_USERS_USERNAME + "= ?";
    private static final String QUERY_ARTICLES = "SELECT * FROM " + TABLE_ARTICLES;

    private static final String QUERY_INSERT_USER = "INSERT INTO " + TABLE_USERS + " VALUES (NULL, ?, ?, ?)";
    private static final String QUERY_ARtiCLE_TYPES = "SELECT * FROM " + TABLE_ARTICLE_TYPES;
    private static final String QUERY_INSERT_ARTICLE = "INSERT INTO " + TABLE_ARTICLES + " VALUES(null, ?, ?, ?, ?, ?)";
    private static final String QUERY_EDIT_ARTICLE = "UPDATE "+ TABLE_ARTICLES+ " SET " + COLUMN_ARTICLE_TITLE + "= ? , " +
            COLUMN_ARTICLE_CONTENT + " = ? , " + COLUMN_ARTICLE_DATE_WRITTEN + "= ?, " + COLUMN_ARTICLE_TYPE_ID + "= ?, " +
            COLUMN_ARTICLE_WRITER_ID + "= ? WHERE " + COLUMN_ARTICLE_ID + "= ?";
    private static final String QUERY_DELETE_ARTICLE = "DELETE FROM " + TABLE_ARTICLES + " WHERE " + COLUMN_ARTICLE_ID +
            " = ?";
    private Connection connection;
    private PreparedStatement queryUsersUsernamePassword;
    private PreparedStatement queryUsersUsername;
    private PreparedStatement queryInsertUser;
    private PreparedStatement queryArticleTypes;
    private PreparedStatement queryInsertArticle;
    private PreparedStatement queryEditArticle;
    private PreparedStatement queryDeleteArticle;


    public static DataSource getInstance() {
        return instance;
    }

    public void open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            queryUsersUsernamePassword = connection.prepareStatement(QUERY_USERS_USERNAME_PASSWORD);
            queryUsersUsername = connection.prepareStatement(QUERY_USERS_USERNAME);
            queryInsertUser = connection.prepareStatement(QUERY_INSERT_USER);
            queryArticleTypes = connection.prepareStatement(QUERY_ARtiCLE_TYPES);
            queryInsertArticle = connection.prepareStatement(QUERY_INSERT_ARTICLE);
            queryEditArticle = connection.prepareStatement(QUERY_EDIT_ARTICLE);
            queryDeleteArticle = connection.prepareStatement(QUERY_DELETE_ARTICLE);
        } catch (SQLException e) {
            System.out.println("Problem when opening db connection " + e.getMessage());
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
        }
    }

    public void close() {
        try {
            if (queryUsersUsernamePassword != null) {
                queryUsersUsernamePassword.close();
            }
            if (queryUsersUsername != null) {
                queryUsersUsername.close();
            }
            if(queryInsertUser != null){
                queryInsertUser.close();
            }
            if(queryArticleTypes != null){
                queryArticleTypes.close();
            }
            if(queryInsertArticle != null){
                queryInsertArticle.close();
            }
            if(queryEditArticle != null){
                queryEditArticle.close();
            }
            if(queryDeleteArticle != null){
                queryDeleteArticle.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Problem when closing db" + e.getMessage());
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
        }

    }

    public List<Article> queryArticles() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_ARTICLES)) {
            List<Article> articles = new ArrayList<>();
            while (resultSet.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return null;
        }
    }

    public List<ArticleJoin> queryArticlesJoin() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_ARTICLES_JOIN);) {
            List<ArticleJoin> articles = new ArrayList<>();
            while (resultSet.next()) {
                ArticleJoin article = new ArticleJoin();
                article.setId(resultSet.getInt(1));
                article.setWriter(resultSet.getString(2));
                article.setWriter_id(resultSet.getInt(3));
                article.setTitle(resultSet.getString(4));
                article.setContent(resultSet.getString(5));
                article.setDate_written(resultSet.getString(6));
                article.setType(resultSet.getString(7));
                article.setType_id(resultSet.getInt(8));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            System.out.println("Problem with database" + e.getMessage());
            return null;
        } catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return null;
        }
    }

    public User queryUserByUsernamePassword(String username, String password) {
        try {
            queryUsersUsernamePassword.setString(1, username);
            queryUsersUsernamePassword.setString(2, password);

            ResultSet resultSet = queryUsersUsernamePassword.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                return user;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return null;
        }
    }

    public User queryUsersByUsername(String username) {
        try {
            queryUsersUsername.setString(1, username);
            ResultSet resultSet = queryUsersUsername.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return null;
        }
    }

    public boolean queryInsertUser(String name, String username, String password){
        try {
            queryInsertUser.setString(1, name);
            queryInsertUser.setString(2, username);
            queryInsertUser.setString(3, password);

            queryInsertUser.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return false;
        }
    }

    public List<ArticleType> queryArticleTypes(){
        List<ArticleType> list = new ArrayList<>();
        try{
            ResultSet resultSet = queryArticleTypes.executeQuery();
            while(resultSet.next()){
                ArticleType articleType = new ArticleType();
                articleType.setId(resultSet.getInt(1));
                articleType.setName(resultSet.getString(2));
                articleType.setDescription(resultSet.getString(3));
                list.add(articleType);
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return null;
        }
    }

    public boolean queryInsertArticle(Article article){
        try{
            queryInsertArticle.setString(1, article.getTitle());
            queryInsertArticle.setString(2, article.getContent());
            queryInsertArticle.setString(3, article.getDate_written());
            queryInsertArticle.setInt(4, article.getType_id());
            queryInsertArticle.setInt(5, article.getWriter_id());
            queryInsertArticle.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return false;
        }
    }

    public boolean queryEditArticle(Article article){
        try{
            queryEditArticle.setString(1, article.getTitle());
            queryEditArticle.setString(2, article.getContent());
            queryEditArticle.setString(3, article.getDate_written());
            queryEditArticle.setInt(4, article.getType_id());
            queryEditArticle.setInt(5, article.getWriter_id());
            queryEditArticle.setInt(6, article.getId());
            int result =  queryEditArticle.executeUpdate();
            return result == 1;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e1){
            System.out.println("Exception " + e1.getMessage());
            e1.printStackTrace();
            return false;
        }
    }
    public boolean queryDeleteArticle(Article article){
        try{
            queryDeleteArticle.setInt(1, article.getId());
            int result = queryDeleteArticle.executeUpdate();
            return result == 1;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e1){
            System.out.println("Exception: " + e1.getMessage());
            e1.printStackTrace();
            return false;
        }
    }
}
