package academy.learnprogramming.algorithm.kmp;

public class KnuthMorrisPratt {


    /**
     * This method returns the first index for the first match of the pattern into the index
     * It will return -1 if no match
     * <pre>
     *                 i
     * 0 1 2 3 4 5 6 7 8 9 1011121314
     * a b a z a c a b a b a b a c
     *
     *                 a b a b a c
     *                 0 0 1 2 3 0
     *                             j
     * returns 8 for this example
     * </pre>
     * @param array
     * @param pattern
     * @return
     */
    public int search(char[] array, char[] pattern) {
        int[] lsp = computeLSPTable(pattern);
        int j=0;
        for (int i=0; i < array.length; i++) {
            while (j>0 && array[i] != pattern[j]) {
                j = lsp[j-1];
            }
            if (array[i] == pattern[j]) {
                j++;
                if (j == pattern.length) {
                    return (i - (j-1));
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param pattern
     * @return
     */
    public int[] computeLSPTable(char[] pattern) {
        int[] lsp = new int[pattern.length];
        int j = 0;
        for (int i=1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = lsp[j-1];
            }
            if (pattern[i] == pattern[j]) {
                lsp[i] = j + 1;
                j++;
            } else {
                lsp[i] = 0;
            }
        }
        return lsp;
    }
}
