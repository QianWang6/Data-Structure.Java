package linkedlist;

import org.w3c.dom.Node;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class JosephuExercis {
    public static void main(String[] args) {
    /*
    设编号为1，2，。。n的n个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始报数，数到m的那个人出列，他的下一位又从1开始报数，数到m的那个人
    又出列，以此类推，直到所有人出列为止，由此产生一个出队编号的序列(新的环形链表）
     */
        //一、创建环形链表并遍历
        //1.先创建第一个节点，让first指向该节点，并形成环状（环形链表）
        //2.后面每创建一个新的节点，就把该节点加入到已有的环形链表中即可
        //遍历原环形链表
        //1.先让一个辅助指针curBoy指向first
        //2.然后通过while循环遍历该环形链表即可curBoy.next==first遍历结束
        //二、根据用户输入，生出出圈的顺序
        //1.需要创建一个辅助指针helper，事先应该指向环形链表的最后的节点
        //报数前，先让first和helper移动k-1次
        //2.当小孩报数时，让first和helper指针同时移动，移动m-1次
        //3.这时可将first指向的节点取出，first= first.next;helper.next = first.next
        //原来first指向的节点就没有任何引用，会被垃圾回收


        //测试构建和遍历是否正常
        JosephuList josephuList = new JosephuList();
        josephuList.addBoy(5);
        josephuList.showBoy();

        josephuList.countBoy(1,2,5);

    }

}

class JosephuList {
    //创建一个first节点，没编号
    private Boy first = new Boy(-1);
    //添加节点，构建成环形链表
    public void addBoy(int nums){
        //nums做一个数据校验
        if (nums<1){
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环来创建环形链表
        for (int i = 1; i <=nums ; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i== 1){
                first = boy;
                first.next = first;
                curBoy = first;//因为first不能动，所以用curboy指向他
            }else {
                curBoy.next = boy;
                boy.next = first;
                curBoy  = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，所以还得使用辅助指针来完成遍历
        Boy curBoy = first;
        while (true){
            System.out.println("小孩编号"+curBoy.no);
            if (curBoy.next == first){//说明遍历完毕
                break;
            }
            curBoy = curBoy.next;
        }
    }
    //根据用户输入，计算出出圈顺序
    public void countBoy(int startNo, int countNum,int nums){
        //startNo:从第几个小孩开始数数
        //countNum：表述数几下
        //nums：表示最初有多少小孩
        if(first == null||startNo<1||startNo>nums){
            System.out.println("数据输入有误");
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        while (true){
            if (helper.next == first){//让helper先指向first的前一个
                break;
            }
            helper = helper.next;
        }
        //报数前，让first和helper移动k-1次
        for (int i = 0; i < startNo-1; i++) {
            first = first.next;
            helper = helper.next;
        }
        //当小孩报数时，两个指针同时移动countNum-1次，然后取出，直到圈中只有一个
        while (true){
            if (helper == first){
                //只有一个人了
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first = first.next;//这时，first指向的节点就是要取出的
                helper = helper.next;
            }
            System.out.println("出圈小孩编号"+first.no);
            first = first.next;
            helper.next = first;
        }
        System.out.println(first.no);


    }



}


//定义一个Boy类，作为节点
class Boy {
    public int no;
    public Boy next;//指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "No." + no;
    }
}
