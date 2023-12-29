package com.nagarro.railway;

/**
 * @author rishabhsinghla TimerService for showing booking details after few
 *         seconds
 */
class TimerService implements CoachObserver {
	private static TimerService instance;

	private TimerService() {

	}

	public static synchronized TimerService getInstance() {
		if (instance == null) {
			instance = new TimerService();
		}
		return instance;
	}

	public void startTimer(int seconds, Runnable action) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		action.run();
	}

	@Override
	public void updateCoachStatus(Coach coach) {
		System.out.println("TimerService: Timer expired for Coach " + coach.getCoachID());
	}

}