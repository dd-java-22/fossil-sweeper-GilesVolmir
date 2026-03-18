---
title: Data Model
description: "UML class diagram, entity-relationship diagram, and DDL."
order: 10
---

{% include ddc-abbreviations.md %}

## Page contents
{:.no_toc:}

- ToC
{:toc}

## UML class diagram

[![UML Class Diagram](img/uml.svg)](pdf/uml.pdf)

## Entity-relationship diagram

[![Entity-Relationship Diagram](img/erd.svg)](pdf/erd.pdf)

### Entity Classes
[Fossil](https://github.com/dd-java-22/fossil-sweeper-GilesVolmir/blob/main/app/src/main/java/edu/cnm/deepdive/fossilsweeper/model/entity/Fossil.java)

[CollectedFossil](https://github.com/dd-java-22/fossil-sweeper-GilesVolmir/blob/main/app/src/main/java/edu/cnm/deepdive/fossilsweeper/model/entity/Fossil.java)

[UserProfile](https://github.com/dd-java-22/fossil-sweeper-GilesVolmir/blob/main/app/src/main/java/edu/cnm/deepdive/fossilsweeper/model/entity/UserProfile.java)

[DigSiteGrid](https://github.com/dd-java-22/fossil-sweeper-GilesVolmir/blob/main/app/src/main/java/edu/cnm/deepdive/fossilsweeper/model/entity/DigSiteGrid.java)

[DigSiteSquare](https://github.com/dd-java-22/fossil-sweeper-GilesVolmir/blob/main/app/src/main/java/edu/cnm/deepdive/fossilsweeper/model/entity/DigSiteSquare.java)

### Database Class
[FossilSweeperDatabase](https://github.com/dd-java-22/fossil-sweeper-GilesVolmir/blob/main/app/src/main/java/edu/cnm/deepdive/fossilsweeper/service/FossilSweeperDatabase.java)

### DDL
{% include linked-file.md type="sqlite" file="sql/ddl.sql" %}
