package hellojava.practice;

public class Damagochi {
		final String name;
		final String str_type;
		// TODO toatal hp_bar
		private int hp_bar = 100;
		private int exp = 0;
		int int_lv = 1;
		private int old_lv = 1;
		public Damagochi(String name, String str_type){
			this.name = name;
			this.str_type = str_type;
			System.out.println(this.name + " TYPE: "+ str_type + "ㅋㅊㅋㅊㅋㅊ");
		}
		
		// 경험치 올리기
		void exp_develop() {
			if(this.int_lv < 4) {
				if(this.exp < 100) {
					this.exp += 3;
				}else {
					this.exp = this.exp - 100;
					lv_up();
				}
			}else {
				this.exp = 100;
				System.out.println("FULL EXP");
				lv_up();
			}
		}
		// 레벨업 관리
		private void lv_up() {
			if(this.int_lv == 4) {
				System.out.println("만렙!");
			}else {
				this.old_lv = this.int_lv++;
				int hp_up_point = 5 * this.int_lv;
				hp_manage(hp_up_point);
				System.out.println(old_lv+ "=>"+this.int_lv +"LV UP!!");
			}
		}
		
		// 레벨업시 체력시 증가
		private void hp_manage(int hp_point) {
			if(this.old_lv != this.int_lv) {
				int old_hp_bar = this.hp_bar; 
				this.hp_bar += hp_point;
				System.out.printf("HP: %d + %d => %d \n", old_hp_bar, hp_point, this.hp_bar); 
			}
		}
		
		// 전체 상태 출력
		@Override
		public String toString() {
			return name + str_type + hp_bar + exp + int_lv; 
		}
		
}
