package linkedlist;

import org.w3c.dom.Node;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "报仇头");
        HeroNode hero5 = new HeroNode(5, "小白", "嗡嗡嗡");
        HeroNode hero6 = new HeroNode(6, "小红", "不不不");
        HeroNode hero7 = new HeroNode(7, "小驴", "嗷嗷嗷");
        //创建列表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.showSingleLinkedList();*/

        //按顺序

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero7);
        singleLinkedList.addByOrder(hero6);
        singleLinkedList.showSingleLinkedList();

        //测试修改节点的代码
        System.out.println("====修改前====");
        singleLinkedList.showSingleLinkedList();
        HeroNode newhero = new HeroNode(2, "卢卢", "玉麒麟！！！");
        System.out.println("====修改后====");
        singleLinkedList.update(newhero);
        singleLinkedList.showSingleLinkedList();
        //测试删除节点
        System.out.println("====删除后====");
        singleLinkedList.remove(1);
        singleLinkedList.showSingleLinkedList();

        //测试计数
        System.out.println(countlink(singleLinkedList));
        //测试倒数第k个
        System.out.println(findeLastIndexNode(singleLinkedList,3));
        //测试翻转
        reverse(singleLinkedList).showSingleLinkedList();

    }

    //获取单链表的有效节点个数（如果有头节点，要把头节点去掉）
    public static int countlink(SingleLinkedList singleLinkedList) {
        int num = 0;
        if (singleLinkedList.getHead() != null) {
            HeroNode temp = singleLinkedList.getHead();
            while (true) {
                if (temp.next == null) {
                    return num;
                }
                temp = temp.next;
                num++;
            }
        }
        return num;
    }

    //打印倒数第k个节点
    //1.编写一个方法接收head节点，同时接收一个index
    //2.index表示倒数第index个节点
    //3.先把链表从头到尾遍历一遍，得到链表总长度，
    // 4.从列表第一个开始遍历，遍历长度减去index个
    public static HeroNode findeLastIndexNode(SingleLinkedList singleLinkedList, int index) {
        HeroNode res = singleLinkedList.getHead().next;
        if (res == null){
            System.out.println("链表为空");
            return null;
        }else {
            int size = countlink(singleLinkedList);
            //要做index校验
            if (index<=0||index>size){
                return null;
            }
            for (int i = 0; i < size - index; i++) {
                res = res.next;
            }
        }
        return res;
    }

    //翻转链表
    //1.新建一个链表头reverseHead
    //2.遍历原链表，把每一个节点连接到reverseHead上
    //3.把原head指向reverseHead
    public static SingleLinkedList reverse(SingleLinkedList singleLinkedList){
        HeroNode reverseHead = new HeroNode(0,"","");
        if (singleLinkedList.getHead().next==null||singleLinkedList.getHead() == null){
            return singleLinkedList;
        }
        while (singleLinkedList.getHead().next!=null){
            HeroNode temp = singleLinkedList.getHead().next;
            singleLinkedList.getHead().next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }
        singleLinkedList.getHead().next = reverseHead.next;
        return singleLinkedList;
    }


}


//定义一个singlelinkedlist管理英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");


    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode) {
        //当不考虑编号顺序时，1.找到当前链表最后一个2.将最后节点的next指向新节点
        //因为head节点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;//让temp后移
        }
        //此时temp是链表结尾
        temp.next = heroNode;
    }

    //修改节点信息，根据no编号来修改，即no不能修改
    //1.根据传入对象的no来修改即可
    public void update(HeroNode heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        //定义一个辅助变量temp
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                //说明链表遍历结束
                break;
            }
            if (temp.no == heroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的内容
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.printf("没有找到编号为？%d的节点", heroNode.no);
        }
    }

    //删除节点
    public void remove(int no) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要删除的节点
        //定义一个辅助变量temp直接指向要删除节点的前一个节点
        HeroNode temp = head;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp.next == null) {
                //说明链表遍历结束
                break;
            }
            if (temp.next.no == no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要删除的内容
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号为？%d的节点", no);
        }
    }


    //显示链表[遍历]
    public void showSingleLinkedList() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //不为空
        HeroNode temp = head.next;//直接指向第一个节点
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //第二种添加方法（按排名添加，若添加失败，给出提示）
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，通过temp指针（变量）来找到添加位置
        //单链表，找的temp位置是位于添加位置的前一个节点
        HeroNode temp = head;
        boolean flag = false;//标注填入的编号是否存在，默认不存在
        while (true) {
            if (temp.next == null) {//temp在链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {//说明被添加的数据应该在temp和temp.next之间
                break;
            } else if (temp.next.no == heroNode.no) {//说明被添加的编号已经存在了
                flag = true;//说明编号存在了
                break;
            }
            temp = temp.next;//后移
        }
        //判断flag值
        if (flag) {//说明编号已存在
            System.out.printf("插入编号%d已存在，不能加入\n", heroNode.no);
        } else {
            //插入链表
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


}

//定义一个heronode，作为节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "No." + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + "}";
    }
}