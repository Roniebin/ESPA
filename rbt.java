// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.util.StringTokenizer;

// class Node {
// int data;
// Node left;
// Node right;
// Node parent;
// String color;

// public Node(int data) {
// this.data = data;
// this.left = null;
// this.right = null;
// this.parent = null;
// this.color = "BLACK";
// }
// }

// public class rbt {
// Node root;

// public rbt() {
// this.root = null;
// }

// public static Node TreeSearch(Node x, int data) {
// while (x != null && x.data != -1 && data != x.data) {
// if (data < x.data) {
// x = x.left;
// } else {
// x = x.right;
// }
// }
// return x;

// }

// public static void leftRotate(rbt T, Node x) {
// Node y = x;
// Node m = x.right.left;

// if (T.root == x) {
// x.right.left = null;
// x = x.right;
// x.left = y;
// y.parent = x;
// x.parent = null;
// T.root = x;
// y.right = m;
// m.parent = y;

// } else {
// Node anc = x.parent;
// x.right.left = null;
// x = x.right;
// x.left = y;
// y.parent = x;
// if (anc.data > x.data) {
// anc.left = x;
// } else {
// anc.right = x;
// }
// x.parent = anc;
// y.right = m;
// m.parent = y;
// }

// }

// public static void makenil(Node x) {

// if (x.left == null) {
// Node nil_left = new Node(-1);
// x.left = nil_left;
// nil_left.parent = x;
// }
// if (x.right == null) {
// Node nil_right = new Node(-1);
// x.right = nil_right;
// nil_right.parent = x;
// }

// }

// public static void rightRotate(rbt T, Node x) {
// Node y = x;
// Node m = x.left.right;

// if (T.root == x) {
// x.left.right = null;
// x = x.left;
// x.right = y;
// y.parent = x;
// x.parent = null;
// T.root = x;
// y.left = m;
// m.parent = y;

// } else {
// Node anc = x.parent;
// x.left.right = null;
// x = x.left;
// x.right = y;
// y.parent = x;
// if (anc.data > x.data) {
// anc.left = x;
// } else {
// anc.right = x;
// }
// x.parent = anc;
// y.left = m;
// m.parent = y;
// }

// }

// public static void RbtInsert(rbt T, Node x) {

// TreeInsert(T, x);
// x.color = "RED";
// Node y = null;

// while (x != T.root && x.parent.color == "RED") {

// if (x.parent == x.parent.parent.left) {
// y = x.parent.parent.right;

// if (y.color == "RED") {
// x.parent.color = "BLACK";
// y.color = "BLACK";
// x.parent.parent.color = "RED";
// x = x.parent.parent;
// } else {
// if (x == x.parent.right) {

// x = x.parent;
// leftRotate(T, x);
// }

// x.parent.color = "BLACK";
// x.parent.parent.color = "RED";
// rightRotate(T, x.parent.parent);
// }
// } else {
// y = x.parent.parent.left;

// if (y.color == "RED") {
// x.parent.color = "BLACK";
// y.color = "BLACK";
// x.parent.parent.color = "RED";
// x = x.parent.parent;
// } else {
// if (x == x.parent.left) {

// x = x.parent;
// rightRotate(T, x);
// }

// x.parent.color = "BLACK";
// x.parent.parent.color = "RED";
// leftRotate(T, x.parent.parent);
// }
// }
// }
// T.root.color = "BLACK";
// }

// public static void TreeInsert(rbt T, Node z) {
// Node y = null;
// Node x = T.root;
// while (x != null && x.data != -1) {
// y = x;
// if (z.data < y.data) {
// x = x.left;
// } else {
// x = x.right;
// }
// }
// z.color = "RED";
// z.parent = y;
// if (y == null) {
// T.root = z;
// makenil(T.root);
// } else if (z.data < y.data) {
// y.left = z;
// makenil(y.left);
// } else {
// y.right = z;
// makenil(y.right);
// }
// }

// public static Node TreeMinimum(Node x) {
// while (x.left.data != -1) {
// x = x.left;
// }
// return x;
// }

// public static Node TreeSuccessor(Node x) {

// Node y = null;

// if (x.right != null && x.right.data != -1) {
// return TreeMinimum(x.right);
// }
// y = x.parent;

// while (y != null && y.data != -1 && x.data == y.right.data) {
// x = y;
// y = y.parent;
// }
// return y;
// }

// public static void rbDeleteFixup(rbt T, Node x) {
// Node w = null;
// while (x != T.root && x.color == "BLACK") {
// if (x == x.parent.left) {
// w = x.parent.right;

// if (w.color == "RED") {
// w.color = "BLACK";
// x.parent.color = "RED";
// leftRotate(T, w.parent);
// w = x.parent.right;
// }

// if (w.left.color == "BLACK" && w.right.color == "BLACK") {
// w.color = "RED";

// if (x.parent.color == "RED") {
// x.parent.color = "BLACK";
// break;
// }

// x = x.parent;
// continue;
// } else if (w.right.color == "BLACK") {
// w.left.color = "BLACK";
// w.color = "RED";
// rightRotate(T, w);
// w = x.parent.right;
// }

// w.color = x.parent.color;
// x.parent.color = "BLACK";
// w.right.color = "BLACK";
// leftRotate(T, w.parent);
// x = T.root;
// } else {
// w = x.parent.left;

// if (w.color == "RED") {
// w.color = "BLACK";
// x.parent.color = "RED";
// rightRotate(T, w.parent);
// w = x.parent.left;
// }

// if (w.right.color == "BLACK" && w.left.color == "BLACK") {
// w.color = "RED";

// if (x.parent.color == "RED") {
// x.parent.color = "BLACK";
// break;
// }
// x = x.parent;
// continue;
// } else if (w.left.color == "BLACK") {
// w.right.color = "BLACK";
// w.color = "RED";
// leftRotate(T, w);
// w = x.parent.left;
// }

// w.color = x.parent.color;
// x.parent.color = "BLACK";
// w.left.color = "BLACK";
// rightRotate(T, w.parent);
// x = T.root;
// }
// }
// x.color = "BLACK";
// }

// public static void TreeDelete(rbt T, Node z) {
// Node y = null;
// Node x = null;
// if ((z.left != null && z.left.data == -1) || (z.right != null && z.right.data
// == -1)) {
// y = z;
// } else {
// y = TreeSuccessor(z);
// }

// if (y.left.data != -1) {
// x = y.left;
// } else {
// x = y.right;
// }

// if (x.data != -1 && y != T.root) {
// x.parent = y.parent;
// }

// if (y.parent == null) {
// T.root = x;
// if (x.data != -1) {
// x.parent = null;
// }
// } else if (y == y.parent.left) {
// y.parent.left = x;
// x.parent = y.parent;

// } else {
// y.parent.right = x;
// x.parent = y.parent;
// }

// if (y != z) {
// z.data = y.data;
// }

// if (y.color == "BLACK") {
// rbDeleteFixup(T, x);
// }
// }

// public static void main(String agrs[]) throws Exception {

// rbt T = new rbt();
// StringBuilder sb = new StringBuilder();
// try {
// FileReader fileReader = new FileReader("rbt.inp");
// BufferedReader bufferedReader = new BufferedReader(fileReader);
// FileWriter fileWriter = new FileWriter("rbt.out");
// BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

// while (true) {
// String line = bufferedReader.readLine();
// StringTokenizer st = new StringTokenizer(line, " ");

// String a = st.nextToken();
// int value = Integer.parseInt(st.nextToken());

// if (a.equals("i")) {
// Node z = new Node(value);

// if (value < 0) {
// break;
// }

// RbtInsert(T, z);

// } else if (a.equals("c")) {
// if (value < 0) {
// break;
// }
// Node temp = TreeSearch(T.root, value);
// sb.append("color(" + value + "): " + temp.color + "\n");

// } else if (a.equals("d")) {
// Node z = TreeSearch(T.root, value);
// if (value < 0) {
// break;
// }
// TreeDelete(T, z);

// }
// }
// String result = sb.toString();
// bufferedWriter.write(result.trim());
// bufferedWriter.flush();

// } catch (Exception e) {

// }
// }

// }
