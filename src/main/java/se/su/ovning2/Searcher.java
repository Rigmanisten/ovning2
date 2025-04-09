package se.su.ovning2;

import java.util.*;
import java.util.stream.Collectors;

public class Searcher implements SearchOperations {
    private final Set<String> artistSet = new HashSet<>();
    private final Set<String> titleSet = new HashSet<>();
    private final Set<String> genreSet = new HashSet<>();
    private final Set<String> sortedtitleSet = new TreeSet<>();

  public Searcher(Collection<Recording> data) {
      Set<Recording> recordingSet = new HashSet<>(data);
      for (Recording r : recordingSet) {
        artistSet.add(r.getArtist());
      }
      for (Recording r : recordingSet) {
        titleSet.add(r.getTitle());
      }
      for (Recording r : recordingSet){
        genreSet.addAll(r.getGenre());
      }
      for (Recording r : recordingSet){
        sortedtitleSet.add(r.getTitle());
      }
  }

  @Override
  public long numberOfArtists() {
    return artistSet.size();
    //throw new UnsupportedOperationException("Unimplemented method 'numberOfArtists'");
  }

  @Override
  public long numberOfGenres() {
    return genreSet.size();
    //throw new UnsupportedOperationException("Unimplemented method 'numberOfGenres'");
  }

  @Override
  public long numberOfTitles() {
    return titleSet.size();
    //throw new UnsupportedOperationException("Unimplemented method 'numberOfTitles'");
  }

  @Override
  public boolean doesArtistExist(String name){
    return artistSet.contains(name);
    //throw new UnsupportedOperationException("Unimplemented method 'doesArtistExist'");
  }

  @Override
  public Collection<String> getGenres() {
    return genreSet;
    //throw new UnsupportedOperationException("Unimplemented method 'getGenres'");
  }

  @Override
  public Recording getRecordingByName(String title) {
    return (Recording) sortedtitleSet;
    //throw new UnsupportedOperationException("Unimplemented method 'getRecordingByName'");
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsAfter'");
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'getRecordingsByArtistOrderedByYearAsc'");
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenre'");
  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenreAndYear'");
  }

  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'offerHasNewRecordings'");
  }
}
