package com.telemed.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.telemed.appointmententity.AppointmentTimeSlot;
import com.telemed.dao.interfaces.AppointmentTimeSlotDao;
import com.telemed.dao.rowmapper.AppointmentTimeSlotMapper;
import com.telemed.exceptions.NoSlotsAvailable;

@Repository
public class AppointmentTimeSlotDaoImpl implements AppointmentTimeSlotDao {
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	private AppointmentTimeSlotMapper appointmentTimeSlotMapper=AppointmentTimeSlotMapper.getRowMapper();
	
	@Override
	public boolean store(AppointmentTimeSlot slot) {
		String storeSlotQuery="""
					INSERT INTO appointment_slot (id,doctor_id,start,end,total_patient,current_patient)
					VALUES(?,?,?,?,?,?)
				""";
		jdbcTemplate.update(storeSlotQuery,
							slot.getId(),
							slot.getDoctorId(),
							slot.getStartTime(),
							slot.getEndTime(),
							slot.getMaxPatientPerSlot(),
							slot.getCurrentNoOfPatient()
							);
		return true;
	}
	
	
	@Override
	public List<AppointmentTimeSlot> extractAll() {
		String extractAppointmentTimeSlots="""
					SELECT * FROM appointment_slot
				""";
		List<AppointmentTimeSlot> slots=null;
		try {			
			slots=jdbcTemplate.query(extractAppointmentTimeSlots,appointmentTimeSlotMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSlotsAvailable();
		}
		return slots;
	}
	
	
	@Override
	public AppointmentTimeSlot extractById(int slotId) {
		String extractSlotByIdQuery="""
					SELECT * FROM appointment_slot WHERE id=?
				""";
		AppointmentTimeSlot slot=jdbcTemplate.queryForObject(extractSlotByIdQuery, appointmentTimeSlotMapper,slotId);
		return slot;
	}
	
	
	@Override
	public List<AppointmentTimeSlot> extractAvailable(int doctorKey) {
		String extractAvailableTimeSlots="""
					SELECT * FROM appointment_slot where doctor_id=? 
				""";
//		AND current_patient < total_patient AND end>CURTIME()
		List<AppointmentTimeSlot> slots=null;
		try {			
			slots=jdbcTemplate.query(extractAvailableTimeSlots,appointmentTimeSlotMapper,doctorKey);
		} catch (EmptyResultDataAccessException e) {
			throw new NoSlotsAvailable();
		}
		return slots;
	}
	
	
	@Override
	public boolean updateSlot(int slotId) {
		String extractCurrentNoOfPatient="""
					SELECT * FROM appointment_slot WHERE id=?
				""";
		String deleteSlotQueryTempString="""
					DELETE FROM appointment_slot WHERE id=?
				""";
		AppointmentTimeSlot slotObject=jdbcTemplate.queryForObject(extractCurrentNoOfPatient,
																   appointmentTimeSlotMapper,slotId);
		jdbcTemplate.update(deleteSlotQueryTempString,slotId);
		slotObject.setCurrentNoOfPatient(slotObject.getCurrentNoOfPatient()+1);
		
		return this.store(slotObject);
	}
}
