package roles.artificer;

public interface Durable {
	
	public void resetDurability();
	public int getDurability();
	public void changeDurability(int i);

}
