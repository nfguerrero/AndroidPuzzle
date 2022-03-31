@Controller
class ThisWillActuallyRun {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World! This is a test. Nothing importany!"
    }

}
