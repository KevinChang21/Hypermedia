import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class HyperLink {
	
	private String id;
	private int x, y, width, height;
	private String name = null;
	private String toVideoName = null;
	private int toVideoFrameIndex;
	private double diff;
	
	HyperLink(int x, int y, int width, int height){
		this.id = UUID.randomUUID().toString();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	HyperLink(int x, int y, int width, int height, String name){
		this.id = UUID.randomUUID().toString();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}
	
	HyperLink(int x, int y, int width, int height, String name, String toVideoName, int toVideoFrameIndex){
		this.id = UUID.randomUUID().toString();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.toVideoName = toVideoName;
		this.toVideoFrameIndex = toVideoFrameIndex;
	}
	
	HyperLink(int x, int y, int width, int height, String name, String id, String toVideoName, int toVideoFrameIndex){
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.toVideoName = toVideoName;
		this.toVideoFrameIndex = toVideoFrameIndex;
	}
	
	HyperLink(JSONObject jsonLink) throws JSONException {
		this.id = jsonLink.getString("id");
		this.x = jsonLink.getInt("x");
		this.y = jsonLink.getInt("y");
		this.width = jsonLink.getInt("width");
		this.height = jsonLink.getInt("height");
		this.name = jsonLink.getString("name");
		this.toVideoName = jsonLink.optString("toVideoName");
		this.toVideoFrameIndex = jsonLink.optInt("toVideoFrameIndex");
	}
	
	public String getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getDiff() {
		return diff;
	}
	
	public void setDiff(double diff) {
		this.diff = diff;
	}
	
	public String getToVideoName() {
		return toVideoName;
	}
	
	public void setToVideoName(String toVideoName) {
		this.toVideoName = toVideoName;
	}
	
	public int getToVideoFrameIndex() {
		return toVideoFrameIndex;
	}
	
	public void setToVideoFrameIndex(int toVideoFrameIndex) {
		this.toVideoFrameIndex = toVideoFrameIndex;
	}
	
	public HyperLink duplicate() {
		return new HyperLink(x, y, width, height, name, id, toVideoName, toVideoFrameIndex);
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("x", x);
		obj.put("y", y);
		obj.put("width", width);
		obj.put("height", height);
		obj.put("name", name);
		obj.put("toVideoName", toVideoName);
		obj.put("toVideoFrameIndex", toVideoFrameIndex);
		return obj;
	}
}
