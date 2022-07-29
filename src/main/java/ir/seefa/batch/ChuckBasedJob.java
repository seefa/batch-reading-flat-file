package ir.seefa.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import ir.seefa.model.Music;
import ir.seefa.mappper.MusicFieldSetMapper;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 2022-07-30 03:20:32
 */
@Configuration
public class ChuckBasedJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    public static String[] tokens = new String[]{"artist.familiarity","artist.hotttnesss","artist.id","artist.latitude","artist.location","artist.longitude","artist.name","artist.similar","artist.terms","artist.terms_freq","release.id","release.name","song.artist_mbtags","song.artist_mbtags_count","song.bars_confidence","song.bars_start","song.beats_confidence","song.beats_start","song.duration","song.end_of_fade_in","song.hotttnesss","song.id","song.key","song.key_confidence","song.loudness","song.mode","song.mode_confidence","song.start_of_fade_out","song.tatums_confidence","song.tatums_start","song.tempo","song.time_signature","song.time_signature_confidence","song.title","song.year"};

    public ChuckBasedJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemReader<Music> itemReader() {
        FlatFileItemReader<Music> itemReader = new FlatFileItemReader<>();
        itemReader.setLinesToSkip(1);
        // TODO: file location form resource folder must move to /home/data directory
        itemReader.setResource(new FileSystemResource("/home/data/music.csv"));

        DefaultLineMapper<Music> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(tokens);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new MusicFieldSetMapper());
        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }

    @Bean
    public Step chunkBasedReadingFlatFileStep() {
        return this.stepBuilderFactory.get("chunkBasedReadingFlatFileStep")
                .<Music, Music>chunk(10)
                .reader(itemReader())
                .writer(items -> {
                    System.out.printf("Received list of music size %d%n", items.size());
                    items.forEach(System.out::println);
                })
                .build();
    }

    @Bean
    public Job chuckOrientedJob() {
        return this.jobBuilderFactory.get("chunkOrientedFlatFileJob")
                .start(chunkBasedReadingFlatFileStep())
                .build();

    }
}
