package se.su.ovning2;

import java.util.*;
import java.util.stream.Collectors;

public class Searcher implements SearchOperations {
    private final Set<String> artistSet = new HashSet<>();
    private final Set<String> titleSet = new HashSet<>();
    private final Set<String> genreSet = new HashSet<>();

    private final Map<String, Recording> titleToRecordings = new HashMap<>();
    private final NavigableMap<Integer, Set<Recording>> recordingsByYear = new TreeMap<>();
    private final Map<String, Set<Recording>> genreToRecordings = new HashMap<>();
    private final Map<String, SortedSet<Recording>> recordingsByArtistByYearAsc = new HashMap<>();


  public Searcher(Collection<Recording> data) {
      Set<Recording> recordingSet = new HashSet<>(data);
      for (Recording r : recordingSet) {
        artistSet.add(r.getArtist());
        titleSet.add(r.getTitle());
        genreSet.addAll(r.getGenre());

        titleToRecordings.put(r.getTitle(), r);
        for (String g : r.getGenre()) {
          genreToRecordings
                  .computeIfAbsent(g, x -> new HashSet<>())
                  .add(r);
        }
        recordingsByYear.computeIfAbsent(r.getYear(), y -> new HashSet<>()).add(r);
        recordingsByArtistByYearAsc
                  .computeIfAbsent(r.getArtist(), a -> new TreeSet<>(Comparator.comparingInt(Recording::getYear)))
                  .add(r);

      }
  }

  @Override
  public long numberOfArtists() {return artistSet.size();}

  @Override
  public long numberOfGenres() {return genreSet.size();}

  @Override
  public long numberOfTitles() {return titleSet.size();}

  @Override
  public boolean doesArtistExist(String name){return artistSet.contains(name);}

  @Override
  public Collection<String> getGenres() {return genreSet;}

  @Override
  public Recording getRecordingByName(String title) {return titleToRecordings.get(title);}

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    Collection<Recording> result = new HashSet<>();
    for (Set<Recording> recordings : recordingsByYear.tailMap(year, true).values()) {
        result.addAll(recordings);
    }
    return Collections.unmodifiableCollection(result);
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    return Collections.unmodifiableSortedSet(recordingsByArtistByYearAsc
            .getOrDefault(artist, new TreeSet<>(Comparator.comparingInt(Recording::getYear))));
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {
    Set<Recording> result = genreToRecordings.getOrDefault(genre, Collections.emptySet());
    return Collections.unmodifiableCollection(result);
  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
    Set<Recording> byGenre = genreToRecordings.getOrDefault(genre, Set.of());
    Set<Recording> result = new HashSet<>();
    for (Recording r : byGenre) {
      int year = r.getYear();
      if (year >= yearFrom && year <= yearTo) {
        result.add(r);
      }
    }
    return Collections.unmodifiableSet(result);
  }


  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'offerHasNewRecordings'");
  }

  @Override
  public Collection<Recording> optionalGetRecordingsBefore(int year) {
    Collection<Recording> result = new HashSet<>();
    for (Set<Recording> recordings : recordingsByYear.headMap(year, false).values()) {
      result.addAll(recordings);
    }
    return Collections.unmodifiableCollection(result);
  }

  @Override
  public Collection<Recording> optionalGetRecordingsFrom(int year) {
    Set<Recording> result = recordingsByYear.getOrDefault(year, Collections.emptySet());
    return Collections.unmodifiableCollection(result);
  }
}
