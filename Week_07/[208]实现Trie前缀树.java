//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 402 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    Trie[] next = new Trie[26];
    boolean isEnd = false;
    /** Initialize your data structure here. */
    public Trie() {
    }
    
    /** Inserts a word into the trie.
     * insert:拿到当前的字母数组next，依次遍历传入的单词的字符
     * 如果当前字符不在next中，生成一个新的节点，并将当前的节点指向新生成的节点
     * 如果当前字符在next中，将该字符所在的节点指向当前的节点
     * 在单词被遍历结束时，给当前所在的节点设置isEndisEnd的标记，表示以当前字符结束可以形成一个字母
     * */
    public void insert(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null) {
                curr.next[c - 'a'] = new Trie();
            }
            curr = curr.next[c - 'a'];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie.
     * 遍历字母的所有字符，如果当前字符所在的节点不存在，返回false，说明单词还没搜完，树已经断了
     * 不断将遍历到的节点赋值给当前节点
     * 注意：searchs和startsWith的区别需要判断当前节点是否是isEnd，如果是的话，证明是搜索到了，但是startsWith则不需要满足此条件
     * */
    public boolean search(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null) return false;
            curr = curr.next[c - 'a'];
        }
        return curr.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String word) {
        Trie curr = this;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null) return false;
            curr = curr.next[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
