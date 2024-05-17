# Explanation on using `@Transactional` with MongoDB

Về cơ bản, MongoDB mặc định `không hỗ trợ Transaction`. Với các phiên bản MongoDB mới và hệ SDK mới (sử dụng được `MongoTransactionManager`) thì có thể sử dụng `@Transactional` với một số điều kiện

1. Phải register `@Bean` cho `MongoTransactionManager` đối với từng database sử dụng. Với một số phiên bản, có thể sử dụng annotation `@EnableTransactionManagement` tại configuration class
2. Khi sử dụng `@Transactional` với một hàm nào, bắt buộc phải `throw` Exception ra khỏi hàm đó, **không được try/catch**. Bản chất của `@Transactional` là khai báo cho Spring tạo ra một proxy (giống như một cái bao an toàn) để thực hiện transaction trong đó. Nếu không có Exception nào phá proxy này, transaction coi như được hoàn tất và sẽ không có gì bị rollback
