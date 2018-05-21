package com.sidgate.tree;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "node")
public class Node implements Serializable {

  @Id
  @Column(unique = true)
  private String id;

  @Column
  private String label;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "node", cascade = ALL, fetch = LAZY)
  private Set<Hierarchy> parents = new HashSet<>();

  @OneToMany(fetch = LAZY, mappedBy = "parent", cascade = ALL)
  private Set<Hierarchy> childNodes = new HashSet<>();

  public Node() {}

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Set<Node> getParents() {
    Set<Node> nodes = new HashSet<>();
    for (Hierarchy h : parents) {
      nodes.add(h.getParent());
    }
    return nodes;
  }

  public String getPrevious(Node parent) {
    for (Hierarchy h : parents) {
      if (h.getParent().getId().equals(parent.getId())) {
        return h.getPrevious();
      }
    }
    return null;
  }

  public String getNext(Node parent) {
    for (Hierarchy h : parents) {
      if (h.getParent().getId().equals(parent.getId())) {
        return h.getNext();
      }
    }
    return null;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String descriptionStr) {
    this.description = descriptionStr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return Objects.equals(id, node.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public void addParent(Node parent) {
    Hierarchy hierarchy = new Hierarchy(parent, this);
    parents.add(hierarchy);
    parent.childNodes.add(hierarchy);
  }

  public void setNext(Node parent, String nextId) {
    for (Hierarchy h : parents) {
      if (h.getNode().equals(parent)) {
        h.setNext(nextId);
        return;
      }
    }
  }

  public void setPrevious(Node parent, String previousId) {
    for (Hierarchy h : parents) {
      if (h.getNode().equals(parent)) {
        h.setPrevious(previousId);
        return;
      }
    }
  }
}
