package com.sidgate.tree;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeNodeRepository extends JpaRepository<Node, String> {
}
