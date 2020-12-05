package sample.model;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_MUSIC = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:F:\\[FreeCourseSite.com] Udemy - Java Programming Masterclass for Software Developers\\JAVA MASTERCLASS PERSONAL CODES\\SECTION19\\Music Database\\" + DB_MUSIC;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUMS_ID = "_id";
    public static final String COLUMN_ALBUMS_NAME = "name";
    public static final String COLUMN_ALBUMS_ARTIST = "artist";
    public static final int INDEX_ALBUMS_ID = 1;
    public static final int INDEX_ALBUMS_NAME = 2;
    public static final int INDEX_ALBUMS_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";
    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTISTS_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";
    public static final int INDEX_SONGS_ID = 1;
    public static final int INDEX_SONGS_TRACK = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_DESC = 2;
    public static final int ORDER_BY_ASC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + " FROM " + TABLE_ALBUMS + " INNER JOIN " + TABLE_ARTISTS +
                    " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ARTIST + " =" + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID + " WHERE " +
                    TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "=";
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTISTS_FOR_SONG =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," + TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + "," + TABLE_SONGS + "." + COLUMN_SONGS_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONGS_ALBUM + "=" + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ARTIST + "=" + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONGS_TITLE + "=";
    public static final String QUERY_ARTISTS_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," + TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + " COLLATE NOCASE ";

    public static final String TABLE_VIEW_NAME = "artists_list";
    public static final String CREATE_VIEW_FOR_SHOW =
            "CREATE VIEW IF NOT EXISTS " + TABLE_VIEW_NAME + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," +
                    TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + " AS " + COLUMN_SONGS_ALBUM + "," + TABLE_SONGS + "." + COLUMN_SONGS_TRACK + "," +
                    TABLE_SONGS + "." + COLUMN_SONGS_TITLE + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONGS_ALBUM + "=" + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ARTIST + "=" + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID +
                    " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," + TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + "," + TABLE_SONGS + "." + COLUMN_SONGS_TRACK;

    //SELECT name,album,track FROM artists_list WHERE title= "Go Your Own Way"
    public static final String QUERY_FROM_VIEW =
            "SELECT " + COLUMN_ARTISTS_NAME + "," + COLUMN_SONGS_ALBUM + "," + COLUMN_SONGS_TRACK + " FROM " + TABLE_VIEW_NAME +
                    " WHERE " + COLUMN_SONGS_TITLE + "=";

    //prepared statement(for avoiding sql injection)
    public static final String QUERY_FROM_VIEW_PREP =
            "SELECT " + COLUMN_ARTISTS_NAME + "," + COLUMN_SONGS_ALBUM + "," + COLUMN_SONGS_TRACK + " FROM " + TABLE_VIEW_NAME +
                    " WHERE " + COLUMN_SONGS_TITLE + "= ?";

    //insert operation using transaction
    public static final String INSERT_ARTIST= "INSERT INTO "+TABLE_ARTISTS+
            '('+COLUMN_ARTISTS_NAME+')'+" VALUES(?)";
    public static final String INSERT_ALBUM="INSERT INTO "+TABLE_ALBUMS+
            '('+COLUMN_ALBUMS_NAME+","+COLUMN_ALBUMS_ARTIST+')'+" VALUES(?,?)";
    public static final String INSERT_SONG="INSERT INTO "+TABLE_SONGS+
            '('+COLUMN_SONGS_TRACK+","+COLUMN_SONGS_TITLE+","+COLUMN_SONGS_ALBUM+')'+" VAlUES(?,?,?)";

    public static final String QUERY_ARTIST="SELECT "+COLUMN_ARTISTS_ID+" FROM "+TABLE_ARTISTS+" WHERE "+COLUMN_ARTISTS_NAME+"=?";
    public static final String QUERY_ALBUM="SELECT "+COLUMN_ALBUMS_ID+" FROM "+TABLE_ALBUMS+" WHERE "+COLUMN_ALBUMS_NAME+"=?";
    public static final String QUERY_SONG="SELECT "+COLUMN_SONGS_ID+" FROM "+TABLE_SONGS+" WHERE "+COLUMN_SONGS_TITLE+"=?";

    public static final String QUERY_ALBUMS_BY_ARTIST_ID="SELECT * FROM "+TABLE_ALBUMS+" WHERE "+COLUMN_ALBUMS_ARTIST+"=? ORDER BY "+
            COLUMN_ALBUMS_NAME+" COLLATE NOCASE";

    public static final String UPDATE_ARTISTS="UPDATE "+TABLE_ARTISTS+" SET "+COLUMN_ARTISTS_NAME+"=?"+" WHERE "+COLUMN_ARTISTS_ID+"=?";

    private Connection connection;

    private PreparedStatement preparedStatement;

    private PreparedStatement insertartist;
    private PreparedStatement insertalbum;
    private PreparedStatement insertsong;

    private PreparedStatement queryartist;
    private PreparedStatement queryalbum;
    private PreparedStatement querysong;

    private PreparedStatement queryalbumsbyartistid;
    private PreparedStatement updateartist;

    private static Datasource datasource=new Datasource(); //making singleton

    private Datasource(){

    }

    public static Datasource getInstance(){
        return datasource;
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            preparedStatement = connection.prepareStatement(QUERY_FROM_VIEW_PREP);

            insertartist=connection.prepareStatement(INSERT_ARTIST,Statement.RETURN_GENERATED_KEYS);  //returning key(_id) for artist.so,we can add or check
            insertalbum=connection.prepareStatement(INSERT_ALBUM,Statement.RETURN_GENERATED_KEYS);
            insertsong=connection.prepareStatement(INSERT_SONG); //this time no need for key(_id).as we have already generate key for album and artist.if those already exist or not,doesnt matter in song.if song already exists,no matter,as many song may have one name

            queryartist=connection.prepareStatement(QUERY_ARTIST);
            queryalbum=connection.prepareStatement(QUERY_ALBUM);
            querysong=connection.prepareStatement(QUERY_SONG);

            queryalbumsbyartistid=connection.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);
            updateartist=connection.prepareStatement(UPDATE_ARTISTS);

            return true;
        } catch (SQLException e) {
            System.out.println("couldnt find:" + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(insertartist!=null){
                insertartist.close();
            }
            if(insertalbum!=null){
                insertalbum.close();
            }
            if(insertsong!=null){
                insertsong.close();
            }
            if(queryartist!=null){
                queryartist.close();
            }
            if(queryalbum!=null){
                queryalbum.close();
            }
            if(querysong!=null){
                querysong.close();
            }
            if(queryalbumsbyartistid!=null){
                queryalbumsbyartistid.close();
            }
            if(updateartist!=null){
                updateartist.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Artist> queryartists(int order) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (order != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTISTS_NAME);
            sb.append(" COLLATE NOCASE ");
            if (order == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        //we can use try-with-resources as it closes automatically and no need of finally.the first way at atom helper
        //second way (effecient)
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                try {
                    Thread.sleep(20);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(INDEX_ARTISTS_ID));   //COLUMN_ARTISTS_ID
                artist.setName(resultSet.getString(INDEX_ARTISTS_NAME));  //COLUMN_ARTISTS_NAME
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Album> queryalbumbyartist(int id){
        try {
            queryalbumsbyartistid.setInt(1, id);
            ResultSet resultSet=queryalbumsbyartistid.executeQuery();

            List<Album> albums=new ArrayList<>();
            while(resultSet.next()){
                Album album=new Album();
                album.setId(resultSet.getInt(1));
                album.setName(resultSet.getString(2));
                album.setArtist(id);
                albums.add(album);
            }
            return albums;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> artistquerytogetalbums(String name, int order) {

        String string = QUERY_ALBUMS_BY_ARTIST_START + "'" + name + "'";
        StringBuilder sb = new StringBuilder(string);
        if (order != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (order == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<String> albums_name = new ArrayList<>();
            while (resultSet.next()) {
                String s = resultSet.getString(COLUMN_ALBUMS_NAME);
                albums_name.add(s);
            }
            return albums_name;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        //see atom for another method
    }


    public void querysongmetadata() {

        String string = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(string)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnnumber = metaData.getColumnCount();
            for (int i = 1; i <= columnnumber; i++) {
                System.out.format("column no %d describing %s\n", i, metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void count(String table) {

        String string = "SELECT COUNT(*) FROM " + table;
        String s = "SELECT COUNT(*),MIN(_id) FROM " + table;
        String t = "SELECT COUNT(*) AS count,MIN(_id) AS min_id FROM " + table;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(string);
             ResultSet resultSet1 = statement.executeQuery(s);
             ResultSet resultSet2 = statement.executeQuery(t)) {

            int c = resultSet.getInt(1);
            int c1 = resultSet1.getInt(1);
            int m = resultSet1.getInt(2);
            System.out.format("count=%d for first\n", c);
            System.out.format("count=%d and min=%d\n", c, m);

            int k = resultSet2.getInt("count");
            int l = resultSet2.getInt("min_id");
            System.out.format("count %d and min %d", k, l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean view_show_query() {

        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_VIEW_FOR_SHOW);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private int intsertartist(String name) throws SQLException{

        queryartist.setString(1,name);
        ResultSet resultSet=queryartist.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }else{
            //insert the artist
            insertartist.setString(1,name);
            int affectedrows=insertartist.executeUpdate();  //means how many rows it affects

            if(affectedrows!=1){  //more than one row is not expected
                throw new SQLException("couldn't insert artist");
            }

            ResultSet generatedkeys=insertartist.getGeneratedKeys();

            if(generatedkeys.next()){
                return generatedkeys.getInt(1);
            }else{
                throw new SQLException("couldnt get id for the artist");
            }
        }
    }

    public boolean updateartist(int id,String name){
        try{
            updateartist.setString(1,name);
            updateartist.setInt(2,id);

            int affected=updateartist.executeUpdate();
            return affected==1;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private int insertalbum(String name,int artistid) throws SQLException{

        queryalbum.setString(1,name);
        ResultSet resultSet=queryalbum.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }else{
            insertalbum.setString(1,name);
            insertalbum.setInt(2,artistid);

            int affectedrows=insertalbum.executeUpdate();

            if(affectedrows!=1){
                throw new SQLException("cant insert album");
            }
            ResultSet generatedkeys=insertalbum.getGeneratedKeys();

            if(generatedkeys.next()){
                return generatedkeys.getInt(1);
            }else {
                throw new SQLException("cant find album id");
            }
        }
    }

    public void insertsong(String title,String artist,String album,int track){
        try {
            querysong.setString(1, title);
            ResultSet resultSet=querysong.executeQuery();
            if(resultSet.next()){
                throw new SQLException("song already exists");
            }
        }catch (SQLException e){
            e.printStackTrace();
            return;
        }

        try {
            connection.setAutoCommit(false);

            int artist_id = intsertartist(artist);
            int album_id = insertalbum(album, artist_id);
            insertsong.setInt(1, track);
            insertsong.setString(2, title);
            insertsong.setInt(3, album_id);

            int affectedrows=insertsong.executeUpdate();
            if(affectedrows==1){
                connection.commit();
            }else{
                throw new SQLException("cant add the song,more than 1 row affected");
            }
        }catch (Exception e){
            try{
                System.out.println("cant add song");
                connection.rollback();
            }catch (SQLException e2){
                System.out.println("cant roll back");
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            }catch (SQLException e){
                System.out.println("cant autocommit to true");
            }
        }
    }


}
