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

    //delete
	@DeleteMapping("/delete/{id}")
	public String DeleteOne(@PathVariable("id") K id)
	{
		if(object.get(id) != null)
		{
			object.remove(id);
			return "The Details are Deleted";
		}
		return "404 couldn't find the object";
		
	}

    //update 
	@PutMapping("/update/{id}")
    public String updateOne(@PathVariable("id") K id, @RequestBody T objects) 
	{
		 if(this.object.get(id) != null)
		 {
            this.object.put(id, objects);
            return "The details are updated";
        }
        return "404 couldn't find the object";
    }
    
}
