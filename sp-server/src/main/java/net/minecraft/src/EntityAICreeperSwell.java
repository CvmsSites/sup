package net.minecraft.src;

public class EntityAICreeperSwell extends EntityAIBase {
	/** The creeper that is swelling. */
	EntityCreeper swellingCreeper;

	/**
	 * The creeper's attack target. This is used for the changing of the creeper's
	 * state.
	 */
	EntityLiving creeperAttackTarget;

	public EntityAICreeperSwell(EntityCreeper par1EntityCreeper) {
		this.swellingCreeper = par1EntityCreeper;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		EntityLiving var1 = this.swellingCreeper.getAttackTarget();
		return this.swellingCreeper.getCreeperState() > 0
				|| var1 != null && this.swellingCreeper.getDistanceSqToEntity(var1) < 9.0D;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.swellingCreeper.getNavigator().clearPathEntity();
		this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		this.creeperAttackTarget = null;
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		if (this.creeperAttackTarget == null) {
			this.swellingCreeper.setCreeperState(-1);
		} else if (this.swellingCreeper.getDistanceSqToEntity(this.creeperAttackTarget) > 49.0D) {
			this.swellingCreeper.setCreeperState(-1);
		} else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget)) {
			this.swellingCreeper.setCreeperState(-1);
		} else {
			this.swellingCreeper.setCreeperState(1);
		}
	}
}
