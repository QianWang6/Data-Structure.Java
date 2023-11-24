package linkedlist;

/**
 * @author Qian Wang
 * @version 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "报仇头");
        HeroNode2 hero5 = new HeroNode2(5, "小白", "嗡嗡嗡");
        HeroNode2 hero6 = new HeroNode2(6, "小红", "不不不");
        HeroNode2 hero7 = new HeroNode2(7, "小驴", "嗷嗷嗷");
        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero5);
        doubleLinkedList.add(hero6);
        doubleLinkedList.add(hero7);

        //打印
        doubleLinkedList.list();
        //修改
        HeroNode2 newHeroNode = new HeroNode2(7, "小马", "谁谁谁");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表");
        doubleLinkedList.list();
        //删除
        doubleLinkedList.remove(3);
        System.out.println("删除后");
        doubleLinkedList.list();


    }
}

//创建一个双向链表类
class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }
    //遍历双向链表的方法
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //不为空
        HeroNode2 temp = head.next;//直接指向第一个节点
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加
    //默认添加到双向链表结尾处
    public void add(HeroNode2 heroNode) {
        //当不考虑编号顺序时，1.找到当前链表最后一个2.将最后节点的next指向新节点
        //因为head节点不能动，因此需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;//让temp后移
        }
        //此时temp是链表结尾
        //构成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点的内容
    public void update(HeroNode2 heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        //定义一个辅助变量temp
        HeroNode2 temp = head.next;
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

    //删除一个节点
    //双向链表直接找到要删除的节点，然后自我删除
    public void remove(int no) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要删除的节点
        //定义一个辅助变量temp
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                //说明链表遍历结束
                break;
            }
            if (temp.no == no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要删除的内容
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next!=null){
                //如果是最后一个节点，就不需要执行下面这句话了，否则会出现空指针
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号为？%d的节点", no);
        }
    }

}


class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点，默认为null

    public HeroNode2(int no, String name, String nickname) {
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