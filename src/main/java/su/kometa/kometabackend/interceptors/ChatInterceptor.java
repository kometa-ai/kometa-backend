package su.kometa.kometabackend.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import su.kometa.kometabackend.constants.AttributesConstants;
import su.kometa.kometabackend.exceptions.ChatNotFoundException;
import su.kometa.kometabackend.services.ChatService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatInterceptor implements HandlerInterceptor {

    private final ChatService chatService;

    private static final Pattern CHANNEL_ID_PATTERN = Pattern.compile("/channels/(\\d+)");

    public ChatInterceptor(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws ChatNotFoundException {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) return true;

        String uri = request.getRequestURI();
        Matcher matcher = CHANNEL_ID_PATTERN.matcher(uri);

        if (!matcher.find()) {
            throw new ChatNotFoundException();
        }

        long id = Long.parseLong(matcher.group(1));
        request.setAttribute(AttributesConstants.CHAT, chatService.getById(id));

        return true;
    }
}
