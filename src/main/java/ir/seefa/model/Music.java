package ir.seefa.model;

import java.util.Date;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 7/30/22 T 03:24
 */
public class Music {

    private String artistId;
    private String artistName;
    private String terms;
    private String songId;
    private Double songDuration;
    private Float songTempo;
    private Date songDate;
    private String songTitle;

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public Double getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(Double songDuration) {
        this.songDuration = songDuration;
    }

    public Float getSongTempo() {
        return songTempo;
    }

    public void setSongTempo(Float songTempo) {
        this.songTempo = songTempo;
    }

    public Date getSongDate() {
        return songDate;
    }

    public void setSongDate(Date songDate) {
        this.songDate = songDate;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    @Override
    public String toString() {
        return "Music {" +
                "artistId='" + artistId + '\'' +
                ", artistName='" + artistName + '\'' +
                ", terms='" + terms + '\'' +
                ", songId='" + songId + '\'' +
                ", songDuration=" + songDuration +
                ", songTempo=" + songTempo +
                ", songDate=" + songDate +
                ", songTitle='" + songTitle + '\'' +
                '}';
    }
}
