# VertexSearch
Auxiliary program for solving the vertex search problem (written in Java).

The program solves the following subtasks:
* Task1_1. For a given vertex, return a set of adjacent vertices.
* Task1_2. Compute the number of connected components of the graph.
* Task1_3. Determine whether the graph H is a subgraph of the original graph.
* Task2_1. Let Z \in VG, X \in EG. Assuming that the indicated subset of edges X forms a cleared set of edges on the graph, indicate 
  cleared set X', if the players occupy the vertices of the set Z.
* Task2_2. For the resulting pair X' and Z to compose the set of cleared vertices A for the current position.
* Task2_3. Given a vertex v \in VG, which will be put a new player. Define the cleaned vertex set A' after this step.
* Task2_4. Given a vertex u \in VG from the number of occupied by players, u \in Z \cup {v}, which the player will be removed.
  Determine the cleared set of edges X'' after this step.
* Task3_1. Check whether program P (sequence of steps) is valid. If yes, then calculate which largest number of players are located 
  at the vertexes of the graph at a time. If the program P contains an invalid step, return '-1'.
* Task3_2. Check whether the program is winning. If not, then return the cleared subgraph at the moment of the last step for 
  the considered program P.
* Task3_3. Check whether the program is monotonic.
