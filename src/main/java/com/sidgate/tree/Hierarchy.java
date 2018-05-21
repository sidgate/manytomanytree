package com.sidgate.tree;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "hierarchy")
@IdClass(Hierarchy.HierarchyId.class)
public class Hierarchy {

  @Id
  @ManyToOne(cascade = ALL)
  @JoinColumn(name = "node")
  private Node node;

  @Id
  @JoinColumn(name = "parent")
  @ManyToOne(cascade = ALL)
  private Node parent;
  private String previous;
  private String next;

  public Hierarchy(Node parent, Node node) {
    this.parent = parent;
    this.node = node;
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public static class HierarchyId implements Serializable {
    private String node;
    private String parent;

    public HierarchyId() {
    }

    public HierarchyId(String node, String parent) {
      this.node = node;
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      HierarchyId that = (HierarchyId) o;
      return Objects.equals(node, that.node) && Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
      return Objects.hash(node, parent);
    }

    @Override
    public String toString() {
      return node + " parent " + parent;
    }
  }
}
