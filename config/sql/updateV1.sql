alter table base_user_task add unique   index(userId,assignTaskTime);
 
update base_user set `status`=5 where remainTaskNo=0 and `status`=3;
  
update wallet_order  w,base_user u set    w.receviceUid=u.uid   where  u.id=w.receviceUserId ;

update trade_order set `status`=2 where  `status`=0 and source not in(1,7,8) and cardGrade is NULL and LENGTH(remark)>4;


UPDATE  trade_order set taskCycle=0 and  signCycle=0,`status`=3  where source=6;

update  trade_order  set taskCycle=0 ,signCycle=0,`status`=3 where source=3 and remark like '%π‹¿ÌΩ±%'; 

update base_user u  set u.sumProfits=0;

update base_user u  set u.sumProfits=(SELECT IFNULL(SUM(s.amt),0) FROM base_user_sign  s  where u.id=s.userId and s.`status`=2);

update base_user_sign s ,base_user u set s.uid=u.uid where s.userId=u.id and s.uid=0;

update base_user_sign  set rmbCovertEquityScale=1 ,rmbCovertPayAmtScale=1 ,rmbCovertTradeAmtScale=1;


UPDATE base_user_department d,base_user u set d.grade=u.grade where d.userId=u.id and d.uid=u.uid


DELETE  FROM `base_task` where STATUS=2 ;




http://oum0lyvjq.bkt.clouddn.com/%E5%84%BF%E7%AB%A5%E6%AD%8C%E6%9B%B2%20-%20%E5%B0%8F%E9%B8%AD%E5%AD%90%20-%20%E5%8D%95%E6%9B%B2%E7%89%882.mkv
http://oum0lyvjq.bkt.clouddn.com/Fifth%20Harmony%20-%20Body%20Rock.mkv
http://oum0lyvjq.bkt.clouddn.com/SING%E5%A5%B3%E5%9B%A2%20-%20%E5%90%AC%E8%A7%81%E5%A4%8F%E8%87%B3.mkv
http://oum0lyvjq.bkt.clouddn.com/%E9%A2%97%E7%B2%92%20-%20%E6%9F%94%E8%BD%AF%E7%9A%84%E5%85%89.mkv
http://oum0lyvjq.bkt.clouddn.com/%E7%9A%96%E4%B8%9C%E9%98%B3%E5%85%89%20-%20%E6%88%91%E7%88%B1%E4%BD%A0%E4%BA%B2%E7%88%B1%E7%9A%84%20-%20%E5%B9%BF%E5%9C%BA%E8%88%9E%E7%89%88%281%29.mkv


http://oq6gv60qr.bkt.clouddn.com/022621474D4A46029A5CA9E09D9FEA08
http://oq6gaqwwb.bkt.clouddn.com/Fm5oGVy4a5K3SG3n19y80K_HwLz9
http://oq6gaqwwb.bkt.clouddn.com/Fs6TwvZpI0F8ZJXHBHjjzZl3vPOV
http://oq6gaqwwb.bkt.clouddn.com/FqNJPcur0TN99IxEaA8mr-c6cHzs
