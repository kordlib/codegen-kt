import com.google.devtools.ksp.symbol.*
import io.mockk.every
import io.mockk.mockk

val mockKSType = mockk<KSType>()

private fun mockDefaultValueArgument(name: String) = mockValueArgumentInternal(name, null, Origin.SYNTHETIC)
private fun mockValueArgument(name: String, value: Any?) = mockValueArgumentInternal(name, value)

private val emptyAnnotation = mockk<KSAnnotation>()

private val _arguments = listOf(
    mockValueArgument("number", 1),
    mockValueArgument("boolean", true),
    mockValueArgument("clazz", mockKSType),
    mockValueArgument("annotation", emptyAnnotation),
    mockValueArgument("clazzArray", listOf(mockKSType)),
    mockValueArgument("annotationArray", listOf(emptyAnnotation)),
    mockDefaultValueArgument("withDefault")
)

private fun mockValueArgumentInternal(name: String, value: Any?, origin: Origin = Origin.KOTLIN) =
    mockk<KSValueArgument> {
        every { this@mockk.name } returns MockKSName(name)
        every { this@mockk.origin } returns origin
        every { this@mockk.value } returns value
    }

fun mockAnnotation() = mockk<KSAnnotation> {
    every { arguments } returns _arguments
    every { defaultArguments } returns _arguments
}
