package com.example.springbootjpa.ruler;

// isDefinedAt 定义域
// apply 检测是否符合规则
public interface Ruler extends PartialFunction<String,Boolean> {
    default Ruler not() {
        return new Ruler() {
            @Override
            public boolean isDefinedAt(String domain) {
                return Ruler.this.isDefinedAt(domain);
            }

            @Override
            public Boolean apply(String s) {
                return !Ruler.this.apply(s);
            }
        };
    }
}
