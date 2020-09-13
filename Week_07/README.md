学习笔记之双向BFS模板

如果已经知道搜索的开始状态和结束状态， 要找一个满足某种条件的一条路径（一般是最短路径），可以采取双向广度搜索算法，也就是从开始状态和结束状态同时开始搜索，一个向前搜，一个向后找。 这样做的好处是什么？ 我们不妨假设每次搜索的分支因子是r，如果最短的路径长为L的话（也就是搜了L层），那么，用一般的BFS算法（不考虑去掉重复状态），总的搜索状态数是r^L（^表示乘方运算）；而如果采取双向BFS算法，那么，从前往后搜，我们只需要搜索L/2层，从后往前搜，我们也只要搜L/2层，因此，搜索状态数是2*(r^(L/2))，比普通BFS就快了很多了。 双向BFS算法的实质还是BFS，只不过两边同时开始BFS而已。还是可以利用队列来实现：可以设置两个队列，一个用于向前的BFS，另一个用于向后的BFS，利用这两个队列，同时从前、后开始层次遍历搜索树。

//双向BFS

void BFS_bothsides(Node start,Node end)
{
    if(start.state==end.state)//起点终点相同时要特判
    {
        found=true;
        return;
    }
    Queue<Integer> Q1 = new LinkedList<>();// 正向队列
    Queue<Integer> Q2 = new LinkedList<>();// 反向队列
    Q1.offer(start);// 初始状态入正向队列
    Q2.offer(end);// 结束状态入反向队列

    Set<Integer> visited1 = new HashSet<>();// 正向判重数组
    visited1.add(start);
    Set<Integer> visited2 = new HashSet<>();// 反向判重数组
    visited2.add(end);

    while(!Q1.empty() || !Q2.empty())
    {
           if(!Q1.empty())   BFS_expand(Q1,true);  // 在正向队列中搜索
           if(found)  return ;// 搜索结束
                  
           if(!Q2.empty())  BFS_expand(Q2,false);  // 在反向队列中搜索
           if(found)  return ;// 搜索结束
                 
    }
}
void BFS_expand(queue<Status> &Q, bool flag)
{
 	s = Q.poll();// 从队列中得到头结点s
 	for( 每个s 的子节点 t ) //一定要注意这里，仅仅是遍历了头节点的子节点，然后才能两边轮流坐庄
	{
          Node t_node = s.child[i];  // 获取子节点
          if(flag) {   // 在正向队列中判断
               if(!visited1.contain(t_node)) {// 没在正向队列出现过
                 if(visited2.contain(t_node))  { // 在反向队列中出现过
                    process()；
                    found=true；
                    return;
                 }
                 visited1.add[t_node];   // 标记为在在正向队列中
                 Q.push(t_node);  // 入队
               }
           } else {  // 在正向队列中判断
               if (!visited2.contain(t_node)) {// 没在反向队列出现过
                 if(!visited1.contain(t_node))  {// 该状态在正向向队列中出现过
                    process()；
                    found=true；
                    return;
                }
                visited2.add[t_node];  // 标记为在反向队列中
                Q.push(t_node);  // 入队
          }
       }
    }
}



