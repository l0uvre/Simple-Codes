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
