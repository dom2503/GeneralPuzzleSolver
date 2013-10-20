package generalpuzzlesolver;

/**
 * 
 */
public class Conflict{
    private int firstVertex;
    private int secondVertex;
    
    public Conflict(int firstVertex, int secondVertex){
      this.firstVertex = firstVertex;
      this.secondVertex = secondVertex;
    }
    
    public int getFirstVertex(){
      return firstVertex;
    }
    
    public int secondVertex(){
      return secondVertex;
    }
  }
