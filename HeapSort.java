import java.util.Arrays;

public class HeapSort {
    /**  @writer dl
     *   @date  2019-11-14
     *   @introduction   堆排序是通过构造大顶堆后，将顶点和最后一个结点交换后，砍枝 完成的。
     *                  大顶堆的构造：从最后一棵二叉树开始向左向上，将每一个二叉树构造成大顶二叉树
     *                  对于二叉树中结点i：左孩子是2i+1，右孩子是2i+2，父节点是（i-1）/2
    **/
    private static void swap(int[]tree,int a, int b){  //交换数组中的两个值
        int temp=tree[a];
        tree[a]=tree[b];
        tree[b]=temp;
    }
    /**构造最大值在根节点的二叉树**/
    private static void bigTopTree(int[] tree,int n,int i){
        //n为当前树的结点个数，i为当前节点
        int c1=2*i+1;  //左右孩子节点的坐标
        int c2=2*i+2;
        int indexOfMax=i; //找出一个二叉树中出现最大值的节点的坐标，先假设为i；
        if(c1<n && tree[indexOfMax]<tree[c1]){
            indexOfMax=c1;
        }
        if(c2<n && tree[indexOfMax]<tree[c2]){
            indexOfMax=c2;
        }
        if(indexOfMax !=i){
            swap(tree,indexOfMax,i);  //把最大值和节点i进行交换，保证最大值在根节点处
        }
    }
    /**从最后一个非叶子节点开始，构造大顶堆，之后把最大值移到数组末尾**/
    private static void bigTopHeap(int[]tree ,int n){
        int lastNode=n-1;
        int parent=(lastNode-1)/2;
        for (int i=parent;i>=0;i--){
            bigTopTree(tree,n,i);
        }
        swap(tree,0,n-1);
    }

    /**排序时，每次把要排序的堆的最后一个节点舍弃，所以节点个数每次减1 **/
    private  static void Sort(int[]tree,int n){
        for (int i=n;i>0;i--){
            bigTopHeap(tree,i);
        }
    }

    public static void main(String[] args) {
        int[] HeapArr={4,2,5,6,8,1,2,10};
        Sort(HeapArr,8);
        System.out.println(Arrays.toString(HeapArr));
    }

}
