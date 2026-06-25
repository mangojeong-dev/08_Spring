package org.scoula.weather.dto;

import java.util.List;
import lombok.Data;

@Data
public class WeatherDTO {
	private int visibility;          // 가시거리
	private int timezone;           // 시간대
	private Main main;              // 주요 날씨 정보
	private Clouds clouds;          // 구름 정보
	private Sys sys;                // 시스템 정보
	private int dt;                 // 데이터 시간(Unix timestamp)
	private Coord coord;            // 좌표 정보
	private List<WeatherItem> weather; // 날씨 상세 정보
	private String name;            // 도시명
	private int cod;                // 응답 코드
	private int id;                 // 도시 ID
	private String base;            // 기준점
	private Wind wind;              // 바람 정보
}