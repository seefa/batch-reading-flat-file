package ir.seefa.mappper;

import ir.seefa.model.Music;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 7/30/22 T 03:52
 */
public class MusicFieldSetMapper implements org.springframework.batch.item.file.mapping.FieldSetMapper<Music> {
    @Override
    public Music mapFieldSet(FieldSet fieldSet) throws BindException {
        Music music = new Music();

        music.setArtistId(fieldSet.readString("artist.id"));
        music.setArtistName(fieldSet.readString("artist.name"));
        music.setTerms(fieldSet.readString("artist.terms"));
        music.setSongId(fieldSet.readString("song.id"));
        music.setSongDuration(fieldSet.readDouble("song.duration"));
        music.setSongTempo(fieldSet.readFloat("song.tempo"));
        int songYear = fieldSet.readInt("song.year");
        LocalDate songDate = null;
        if(songYear > 1900) {
            songDate = LocalDate.of(songYear, 1, 1);
            ZoneId zoneId = ZoneId.systemDefault();
            music.setSongDate(Date.from(songDate.atStartOfDay(zoneId).toInstant()));
        }
        music.setSongTitle(fieldSet.readString("song.title"));

        return music;
    }
}
