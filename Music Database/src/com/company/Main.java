package com.company;

import com.company.model.Artist;
import com.company.model.Datasource;
import com.company.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource=new Datasource();
        if(!datasource.open()){
            System.out.println("cant open datasource");
            return;
        }
        //query 1
//        List<Artist> artists=datasource.queryartists(Datasource.ORDER_BY_ASC);
//        if(artists==null){
//            System.out.println("no artists!");
//            return;
//        }
//        for(Artist artist:artists){
//            System.out.println(artist.getId()+ " "+artist.getName());
//        }

        //query 2
//        List<String> albums_name=datasource.artistquerytogetalbums("Iron Maiden",Datasource.ORDER_BY_ASC);
//        if(albums_name==null){
//            System.out.println("no albums found");
//            return;
//        }
//        for(String s:albums_name){
//            System.out.println(s);
//        }

        //query 3
//        List<SongArtist> songs=datasource.queryartistforsongs("Go Your Own Way",Datasource.ORDER_BY_ASC);
//        List<SongArtist> songs=datasource.queryartistforsongs("Heartless",Datasource.ORDER_BY_ASC);
//        if(songs==null){
//            System.out.println("no such song exists");
//            return;
//        }
//        for(SongArtist songArtist:songs){
//            System.out.println("Artist: "+songArtist.getArtistname()+" Album: "+songArtist.getAlbumname()+" Track no: "+songArtist.getTrack());
//        }
//
//        //query 4
//        datasource.querysongmetadata();
//
//        //query 5
//        datasource.count(Datasource.TABLE_SONGS);
//        datasource.count("albums");
//        datasource.count("artists");

        //create view
//        datasource.view_show_query();
//
//        //query from view
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Enter The Title of the song:");
//        String string=scanner.nextLine();
////        List<SongArtist> artists=datasource.view_by_query(string);
//        List<SongArtist> artists=datasource.view_by_query_prepared(string);
//        if(artists==null){
//            System.out.println("nothing here");
//            return;
//        }
//        for(SongArtist artist : artists) {
//            System.out.println("FROM VIEW - Artist name = " + artist.getArtistname() +
//                    " Album name = " + artist.getAlbumname() +
//                    " Track number = " + artist.getTrack());
//        }
        //sql injection=Go Your Own Way' or 1=1 or'.hackers use this type of thing.as 1=1 is always true all the data from that table shown

        // SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way" or 1=1 or "" (when it is not a prepared statement)
        // SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way or 1=1 or "" (when it is a prepared statement)

        //insert operation
//        datasource.insertsong("Keu Jane Na","Kala Vut","Kothay Ase",1);
        datasource.insertsong("lagb","uqwr","kow",8);

        datasource.close();

    }

}
