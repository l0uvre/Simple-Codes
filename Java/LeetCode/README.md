## Linked List

### Tricks
 * Create a sentinel node which is better for creating a linked list/ traversal

## Graph

### Notes 
 * When doing BFS/DFS on graph, it's better to mark nodes when expanding the edges. Otherwise, a node that have yet to but should be visited in the stack/queue would be added twice. Sure you can double check whether it's visited when trying to expand it.  --- check my LC 261 two implementations.

## Dynamic Programming

### Concepts / Explanation
 * Dp is approximately recursion + memoization.
 * Dp is approximately careful brute force.
 * Dp is approximately subproblems + "reuse".

### 5 easy steps to dp.
  1. define subproblems.
  2. guess(part of solution).
  3. relate subproblem solutions.
  4. recursion plus memoization
           OR build DP table bottom-up
  5. solve original problem.

### Subproblems for string or sequence
  - suffixes x[i:]
  - prefixes x[:i]
  - substrings x[i:j]

  **When you need both suffixes and prefixes, then you probably need substrings.**


### Acknowledegement
  The content of this file is borrowed from MIT Course 6-006-Introduction to Algorithms by Prof. Erik Demaine that was released under Creative Commons License.
