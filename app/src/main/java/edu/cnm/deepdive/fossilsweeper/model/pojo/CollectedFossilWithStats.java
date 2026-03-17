package edu.cnm.deepdive.fossilsweeper.model.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.List;

public class CollectedFossilWithStats {

  @Embedded
  private CollectedFossil collectedFossil;
  @Relation(
      parentColumn = "fossil_stats_key",
      entityColumn = "fossil_id")
  private List<Fossil> fossils;


  public CollectedFossil getCollectedFossil() {
    return collectedFossil;
  }

  public void setCollectedFossil(
      CollectedFossil collectedFossil) {
    this.collectedFossil = collectedFossil;
  }

  public List<Fossil> getFossils() {
    return fossils;
  }

  public void setFossils(List<Fossil> fossils) {
    this.fossils = fossils;
  }
}
