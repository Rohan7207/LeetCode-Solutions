class Solution {

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litter = new int[m][n];
        int sx = 0, sy = 0, cnt = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if(ch == 'S') {
                    sx = i;
                    sy = j;
                } else if(ch == 'L') {
                    litter[i][j] = 1 << cnt;
                    cnt++;
                }
            }
        }

        int full = 1 << cnt;
        int[][][] bestEnergy = new int[m][n][full];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        bestEnergy[sx][sy][0] = energy;

        class Info {
            int x, y, mask, e, steps;

            Info(int x, int y, int mask, int e, int steps) {
                this.x = x;
                this.y = y;
                this.mask = mask;
                this.e = e;
                this.steps = steps;
            }
        }

        Deque<Info> q = new ArrayDeque<>();
        q.addLast(new Info(sx, sy, 0, energy, 0));

        while(!q.isEmpty()) {
            Info curr = q.removeFirst();

            if(curr.mask == full - 1) {
                return curr.steps;
            }

            if(curr.e == 0) {
                continue;
            }

            for(int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n || classroom[nx].charAt(ny) == 'X') {
                    continue;
                }

                int ne = classroom[nx].charAt(ny) == 'R' ? energy : curr.e - 1;
                int nmask = curr.mask | litter[nx][ny];

                if(ne > bestEnergy[nx][ny][nmask]) {
                    bestEnergy[nx][ny][nmask] = ne;
                    q.addLast(new Info(nx, ny, nmask, ne, curr.steps + 1));
                }
            }
        }

        return -1;
    }
}

/*
    BFS guarantees the first time we reach a state with all litter collected, it uses the minimum number of moves.
*/  

/*
    This is essentially BFS + Bitmask + Energy-state pruning. The important part is understanding what one BFS state represents.

1. What are we actually searching?

At any moment, we need to know four things:

(current row, current column, collected litter, remaining energy)

So one state is:

(x, y, mask, e)

steps is also stored because BFS guarantees the first time we reach a state with all litter collected, it uses the minimum number of moves.

2. What does mask mean?

Suppose there are 3 litter cells:

L1 → bit 0
L2 → bit 1
L3 → bit 2

Then:

000 → collected none
001 → collected L1
101 → collected L1 + L3
111 → collected all

This part assigns each litter a unique bit:

id[i][j] = 1 << cnt;
cnt++;

Then when we step onto a litter cell:

int nmask = t.mask | id[nx][ny];

we mark that litter as collected.

3. Why full = 1 << cnt?

If there are cnt litter cells, there are:

2^cnt

possible masks.

For example, 3 litter:

000
001
010
011
100
101
110
111

Therefore:

int full = 1 << cnt;

The mask containing all litter is:

full - 1

For 3 litter:

1 << 3 = 8
8 - 1 = 7

7 in binary = 111

So:

if (t.mask == full - 1)

means all litter has been collected.

4. Why is BFS used?

Every movement costs exactly 1 step.

So BFS explores:

0 moves
   ↓
1 move
   ↓
2 moves
   ↓
3 moves
...

Therefore, the first state where:

t.mask == full - 1

is reached gives the minimum moves.

5. How is energy handled?

Initially:

bestEnergy[sx][sy][0] = energy;

At every normal movement:

t.e - 1

because moving costs one energy.

But if we move onto R:

int ne = classroom[nx].charAt(ny) == 'R'
        ? energy
        : t.e - 1;

energy is completely restored.

So:

Normal cell → energy - 1
Recharge R  → energy = maximum energy
Wall X      → cannot enter
6. The most important optimization

This is the cleverest part:

if (ne > bestEnergy[nx][ny][nmask]) {
    bestEnergy[nx][ny][nmask] = ne;
    q.addLast(...);
}

Imagine we already reached:

cell = (2,3)
mask = 101
energy = 4

Later we reach the same cell with the same collected litter:

(2,3,101,2)

The second state is useless.

Why?

Because from the exact same position with the exact same litter collected:

energy 4 > energy 2

The state with 4 energy can do everything the state with 2 energy can do—and potentially more.

So we only keep the maximum energy achieved for each (position, mask).

That's what:

bestEnergy[x][y][mask]

means:

Maximum remaining energy with this position and this set of collected litter.

7. Why don't we need energy as another dimension?

You might wonder why this isn't:

visited[x][y][mask][energy]

Instead, we have:

bestEnergy[x][y][mask]

Because energy has a dominance relationship.

For the same:

position + collected litter

higher energy is always better.

So:

(2,3,101,5)

dominates:

(2,3,101,3)

and we don't need to process the weaker state.

8. Complete flow

Suppose:

S . L
. X .
R . L

Starting:

position = S
mask = 000
energy = E
steps = 0

BFS explores possible movements.

Whenever it reaches:

L

the corresponding bit is added.

Whenever it reaches:

R

energy becomes maximum again.

Whenever energy becomes:

0

we stop expanding:

if (t.e == 0) {
    continue;
}

because we cannot make another move.

Eventually:

mask = 111

and BFS immediately returns:

t.steps

which is the minimum number of moves.

Core idea

The entire solution can be remembered as:

BFS
 ↓
Position + collected litter + energy
 ↓
Bitmask tracks litter
 ↓
Energy tracks how far we can continue
 ↓
bestEnergy removes dominated states
 ↓
First full mask = minimum moves

That's why this solution works efficiently instead of trying every possible path.
*/