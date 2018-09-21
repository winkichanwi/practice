import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


class UserSignatureTest {

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ユーザの妥当性検証 {
        List<Fixture> patterns() {
            return Arrays.asList(
                    new Fixture("password", true),
                    new Fixture("passwor", false)
            );
        }

        @ParameterizedTest
        @MethodSource("patterns")
        void パスワード閾値テスト(Fixture pattern) {
            //given
            User user = new UserBuilder().setPassword(pattern.password).build();
            //when
            boolean actual = user.validate();
            //then
            assertThat(pattern.expected, is(actual));
        }

        class Fixture {
            private String password;
            private boolean expected;
            public Fixture(String password, boolean expected) {
                this.password = password;
                this.expected = expected;
            }
        }

        class UserBuilder {
            private String password;
            public UserBuilder setPassword(String password) {
                this.password = password;
                return this;
            }
            public User build() {
                return new User("", "", password);
            }
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 署名の取得 {

        UserDataBuilder[] patterns() {
            return new UserDataBuilder[]{
                    new UserDataBuilder().setExpected("tanaka: PD OK"),
                    new UserDataBuilder().setName("yamada").setExpected("yamada: PD OK"),
                    new UserDataBuilder().setCompanyName("MJ").setExpected("tanaka: MJ OK"),
                    new UserDataBuilder().setPassord("passwor").setExpected("tanaka: PD NG"),
                    new UserDataBuilder().setCompanyName("").setExpected("tanaka OK"),
            };
        }

        @ParameterizedTest
        @MethodSource("patterns")
        void 署名取得パターン(UserDataBuilder pattern) {
            //given
            User user = new User(pattern.name, pattern.companyName, pattern.password);
            //when
            String actual = user.signature();
            //then
            assertThat(pattern.expected, is(actual));
        }

        class UserDataBuilder {
            private String name = "tanaka";
            private String companyName = "PD";
            private String password = "password";
            private String expected;

            UserDataBuilder setName(String name) {
                this.name = name;
                return this;
            }

            UserDataBuilder setCompanyName(String companyName) {
                this.companyName = companyName;
                return this;
            }

            UserDataBuilder setPassord(String s) {
                this.password = s;
                return this;
            }

            UserDataBuilder setExpected(String s) {
                this.expected = s;
                return this;
            }
        }
    }
}

