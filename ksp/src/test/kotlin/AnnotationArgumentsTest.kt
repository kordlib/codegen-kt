import com.google.devtools.ksp.symbol.*
import dev.kord.codegen.ksp.annotations.AnnotationArguments.Companion.arguments
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertSame
import kotlin.test.assertTrue

annotation class OtherAnnotation

annotation class TestAnnotation(
    val number: Int,
    val boolean: Boolean,
    val clazz: KClass<*>,
    val annotation: OtherAnnotation,
    val clazzArray: Array<KClass<*>>,
    val annotationArray: Array<OtherAnnotation>,
    val withDefault: Boolean = false
)

val arguments = mockAnnotation().arguments<TestAnnotation>()

class AnnotationArgumentsTest {
    @Test
    fun `test isDefault`() {
        assertTrue(arguments.isDefault(TestAnnotation::withDefault))
    }

    @Test
    fun `test getNumber`() {
        assertSame(1, arguments[TestAnnotation::number])
    }

    @Test
    fun `test getBoolean`() {
        assertSame(true, arguments[TestAnnotation::boolean])
    }

    @Test
    fun `test getClass`() {
        assertIs<KSType>(arguments[TestAnnotation::clazz])
    }

    @Test
    fun `test getAnnotation`() {
        assertIs<KSAnnotation>(arguments[TestAnnotation::annotation])
    }

    @Test
    fun `test getClassArray`() {
        val value = arguments[TestAnnotation::clazzArray]
        assertIs<List<*>>(value)
        assertIs<KSType>(value.first())
    }

    @Test
    fun `test getAnnotationArray`() {
        val value = arguments[TestAnnotation::annotationArray]
        assertIs<List<*>>(value)
        assertIs<KSAnnotation>(value.first())
    }
}
