import java.util.HashMap;
import java.util.Map;

public class CRUDController<K,T> {
    private Map<K, T> object = new HashMap<K,T>();
	
	public Map<K,T> getMap()
	{
		return object;
	}

    //get All 
	@GetMapping("/")
	public Map<K, T> getAll()
	{
		return object;
	}

    //Add new object
	@PostMapping("/add/{id}")
    public String addNew(@RequestBody T objects, @PathVariable("id") K id) {
        this.object.put(id, objects);
        return "Added new object";
    }
    
}
