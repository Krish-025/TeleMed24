package com.telemed.dao.interfaces;

import java.util.List;

import com.telemed.appointmententity.AppointmentTimeSlot;

public interface AppointmentTimeSlotDao {

	boolean store(AppointmentTimeSlot slot);
	List<AppointmentTimeSlot> extractAll();
	AppointmentTimeSlot extractById(int slotId);
	List<AppointmentTimeSlot> extractAvailable(int doctorKey);
	boolean updateSlot(int slotId);
	
}
