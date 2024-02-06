public class Deadline implements Item {
    public String name;
    public String status;
    public String ddl;

    public Deadline(String name, String status, String ddl) throws RickException{
        if (name.isBlank()) {
            throw new RickException("Nothing is due!");
        }
        if (ddl.isBlank()) {
            throw new RickException("due when?");
        }
        this.name = name;
        this.ddl = ddl;
        this.status = status;
    }
    @Override
    public String toString(){
        return "[D]" + this.status + " " + this.name + " (by: " + ddl + ")";
    }
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
    public String store() {
        return "D|" + this.status + "|" + this.name + "|" + this.ddl;
    }
}