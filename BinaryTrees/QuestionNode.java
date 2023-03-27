public class QuestionNode {
    public String data;
    public QuestionNode trueNode;
    public QuestionNode falseNode;

    public QuestionNode(String data) {
        this(data, null, null);
    }

    public QuestionNode(String data, QuestionNode trueNode, QuestionNode falseNode) {
        this.data = data;
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }
}
