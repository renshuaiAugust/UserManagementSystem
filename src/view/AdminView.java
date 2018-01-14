package view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import service.UserService;

public class AdminView {
	private static UserService userServ = new UserService();
	private static Scanner input = new Scanner(System.in);
	
	
	public static void showMainView(){
		//Scanner input = new Scanner(System.in);
		while(true){
			System.out.println("��ӭʹ���û�����ϵͳ��");
			System.out.println("======================");
			System.out.println("�û���¼-------------1");
			System.out.println("�û�ע��-------------2");
			System.out.println("�˳�����-------------3");
			
			int choice = input.nextInt();
			

			if(choice == 1){
				//�û���¼
				  System.out.println("�����������û�����");
				  String name = input.next();
				  System.out.println("�������������룺");
				  String password = input.next();
				List<String> list = userServ.login(name,password);
				
				if(list == null){
					System.out.println("�û���Ϣ�����ڣ�");
				}else{
					if(list.get(1).equals("��ͨ�û�")){
						System.out.println(list.get(0)+"����:\t����Ȩ���ǣ� ��ͨ�û�");
						System.out.println("=========================");
						showCommonView(list);//��ʾ��ͨ�û�����
						break;
					}
					if(list.get(1).equals("����Ա")){
						System.out.println(list.get(0)+"����:\t����Ȩ���ǣ� ����Ա");
						System.out.println("=========================");
						showAdminView(list);//��ʾ����Ա����
						break;
					}
				}
		     }
			if(choice == 2){
				//�û�ע��
				System.out.println("�������û�����");
				String name = input.next();
				System.out.println("���������룺");
				String password = input.next();
				System.out.println("���������䣺");
				String email = input.next();
				
				if(userServ.insert(name,password,email)){
					System.out.println("�û�ע��ɹ���");
				}
				else{
					System.out.println("�û�ע��ʧ��");
				}
			}
			if(choice == 3){
				System.exit(1);
			}
		}
		input.close();
	}

	//list���汣���˵�ǰ�û���Ϣ
	public static void showAdminView(List<String> list){

		while(true){
			System.out.println("\n");
			System.out.println("����û�--------------1");
			System.out.println("ɾ���û�--------------2");
			System.out.println("�޸��û�--------------3");
			System.out.println("��ѯ�û�--------------4");
			System.out.println("�˳�����--------------5");

			int choice = input.nextInt();
			UserService userServ = new UserService();
			if(choice == 1){
				//����û�
				System.out.println("�������û�����");
				String name = input.next();
				System.out.println("���������룺");
				String password = input.next();
				System.out.println("���������䣺");
				String email = input.next();
				if(userServ.insert(name,password,email)){
					System.out.println("����û��ɹ���");
				}
			}
			if(choice == 2){
				//ɾ���û�
				System.out.println("������Ҫɾ���û���ID����:");
				int id = input.nextInt();
				if(userServ.deleteUser(id)){
					System.out.println("ɾ���ɹ�!");
				}
				else{
					System.out.println("ɾ��ʧ�ܣ�");
				}
			}
			if(choice == 3){
				//�޸��û�
	              System.out.println("������Ҫ�޸ĵ��û���ID���룺");
	              int id = input.nextInt();
	              System.out.println("������Ҫ�޸ĵ��û����û�����");
	              String name = input.next();
	              System.out.println("������Ҫ�޸ĵ��û������룺");
	              String password = input.next();
	              System.out.println("������Ҫ�޸ĵ��û������䣺");
	              String email = input.next();
	              System.out.println("������Ҫ�޸ĵ��û���Ȩ�ޣ�����Ա/��ͨ�û�����");
	              String permission = input.next();
	              if(userServ.modify(list, id,name,password,email,permission)){
	            	  System.out.println("�޸ĳɹ�");
	              }else{
	            	  System.out.println("�޸�ʧ��");
	              }
			}
			if(choice == 4){
				//��ѯ�û�
				queryMethod();
				
			}
			if(choice == 5){
				System.exit(1);
			}
		}
	}
	public static void queryMethod(){
		Iterator<String> iter = null;
    	   System.out.println("��ѯȫ���û�-------------1");
    	   System.out.println("����ID��ѯ�û�-----------2");
    	   System.out.println("����������ѯ�û�--------- 3");
    	   System.out.println("������Ҫ���Ĳ���:");
		
    	   int oper =input.nextInt();
    	   switch(oper){
    	   case 1:{
    		   	Map<String,List<String>> map = userServ.query();
    		   	int count = 0;
    		   	Set<String> set = map.keySet();
    		   	iter = set.iterator();
    		   	while(iter.hasNext()){
    		   		count = Integer.parseInt(iter.next());
    		   	}
    		   	List<String> list = map.get(String.valueOf(count));
    		   	iter = list.iterator();
    		   	String[] str = new String[list.size()];
    		   	int i = 0;
    		   	while(iter.hasNext()){
    		   		str[i] = iter.next();
    		   		i++;
    		   	}
    		   	for(int j=0; j<str.length; j++){
	            	  if(j%(list.size()/count)!=0){
	            		  System.out.print(str[j]+"  ");
	            	  }
	            	  else{
	            		  System.out.println("\n====================================================");
	            		  System.out.print(str[j]+" ");
	            	  }
	              }
    		   	System.out.println("\n");
				  break;
			  }
    	   case 2:{
				  System.out.println("������Ҫ��ѯ���û���ID:");
				  int id = input.nextInt();
				  
				  Map<String,List<String>> map = userServ.query(id);
	              int count = 0;
	              Set<String> set = map.keySet();
	              iter = set.iterator();
	              while(iter.hasNext()){
	            	  count = Integer.parseInt(iter.next());
	              }
	              List<String> list = map.get(String.valueOf(count));
	              iter = list.iterator();
	              String[] str = new String[list.size()];
	              int i = 0;
	              while(iter.hasNext()){
	            	  str[i] = iter.next();
	            	  i++;
	              }
	              for(int j=0; j<str.length; j++){
	            	  if(j%(list.size()/count)!=0){
	            		  System.out.print(str[j]+"  ");
	            	  }
	            	  else{
	            		  System.out.println("\n====================================================");
	            		  System.out.print(str[j]+" ");
	            	  }
	              }
				  break;
			  }
			  case 3:{
				  String name = null;
				  System.out.println("������Ҫ��ѯ���û�������:");
				   name = input.next();
				  Map<String,List<String>> map = userServ.query(name);
	              int count = 0;
	              Set<String> set = map.keySet();
	              iter = set.iterator();
	              while(iter.hasNext()){
	            	  count = Integer.parseInt(iter.next());
	              }
	              List<String> list = map.get(String.valueOf(count));
	              iter = list.iterator();
	              String[] str = new String[list.size()];
	              int i = 0;
	              while(iter.hasNext()){
	            	  str[i] = iter.next();
	            	  i++;
	              }
	              for(int j=0; j<str.length; j++){
	            	  if(j%(list.size()/count)!=0){
	            		  System.out.print(str[j]+"  ");
	            	  }
	            	  else{
	            		  System.out.println("\n====================================================");
	            		  System.out.print(str[j]+" ");
	            	  }
	              }
				  break;
			  }
    	   }
	}
	
	//list���汣���˵�ǰ�û���Ϣ
	public static void showCommonView(List<String> list){
	while(true){
			System.out.println("�޸��Լ�����Ϣ----------1");
			System.out.println("��ѯ�Լ�����Ϣ----------2");
			System.out.println("�˳�����---------------3");
			
			//Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			UserService userServ = new UserService();
			if(choice == 1){
				//�޸��Լ�����Ϣ
				System.out.println("����ǰ����Ϣ�ǣ�");	
				System.out.println("ID:\t ����:\t ����:\t ����:\t\t\t �û�Ȩ�ޣ�\t");
				System.out.println(list.get(4)+"\t"+list.get(0)+"\t"+list.get(2)+"\t"+list.get(3)+"\t"+list.get(1));
				System.out.println("���������û�����");
				String name = input.next();
				System.out.println("�����������룺");
				String password = input.next();
				System.out.println("�����������䣺");
				String email = input.next();
				if(userServ.modify(list, name,password,email)){
					System.out.println("�޸ĳɹ�");
				}else{
					System.out.println("�޸�ʧ��");
				}
			
			}
			if(choice == 2){
				System.out.println("ID:\t ����:\t ����:\t ����:\t\t\t �û�Ȩ�ޣ�\t");
				System.out.println(list.get(4)+"\t"+list.get(0)+"\t"+list.get(2)+"\t"+list.get(3)+"\t"+list.get(1));
			}
			if(choice == 3){
				System.exit(1);
			}
		}
	}
	
	
	
	
	
  public static void main(String[] args){
	  showMainView();
  }
}
