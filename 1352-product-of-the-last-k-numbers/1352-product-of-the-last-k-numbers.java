class ProductOfNumbers {

    private List<Integer> list;

    public ProductOfNumbers() {
        list = new ArrayList<>();
        list.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            list.clear();
            list.add(1);
        } else {
            int last = list.get(list.size() - 1);
            list.add(last * num);
        }
    }

    public int getProduct(int k) {
        if (k >= list.size()) 
            return 0;

        int n = list.size() - 1; // Bcz we added 1
        return list.get(n) / list.get(n - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */

/*
   class ProductOfNumbers {

   private List<Integer> list;

   public ProductOfNumbers() {
       list = new ArrayList<>();
   }
   
   public void add(int num) {
       list.add(num);
   }
   
   public int getProduct(int k) {
       int product = 1;
       int len = list.size();

       for(int i = len - k; i < len; i++) {
           product *= list.get(i);
       }

       return product;
   }
}
*/