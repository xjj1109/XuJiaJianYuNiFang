package bean;

/**
 * Created by asus on 2016/12/10.
 */
public class CartSqlBean {
    private  String  name;
    private  String img;
    private  String price ;
    private  String old_price;
    private boolean flag=false;

    public CartSqlBean(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public CartSqlBean(String name, String img, String price, String old_price) {
        this.name = name;
        this.img = img;
        this.price = price;
        this.old_price = old_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOld_price() {
        return old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    @Override
    public String toString() {
        return "CartSqlBean{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price='" + price + '\'' +
                ", old_price='" + old_price + '\'' +
                '}';
    }

    public CartSqlBean() {

    }
}
