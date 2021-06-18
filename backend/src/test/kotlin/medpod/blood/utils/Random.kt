package medpod.blood.utils

import org.jeasy.random.EasyRandom
import java.util.stream.Stream
import kotlin.streams.toList

object Random {
    val random = EasyRandom()
}

inline fun <reified T : Any> EasyRandom.nextObj(): T =
    nextObject(T::class.java)

inline fun <reified T : Any> EasyRandom.objects(streamSize: Int): Stream<T> =
    objects(T::class.java, streamSize)

inline fun <reified T : Any> EasyRandom.objectsList(streamSize: Int): List<T> =
    objects(T::class.java, streamSize).toList()
