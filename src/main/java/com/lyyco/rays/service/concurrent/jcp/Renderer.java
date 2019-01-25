package com.lyyco.rays.service.concurrent.jcp;

import java.util.List;
import java.util.concurrent.*;

/**
 * JCP  page 107 使用CompletionService实现页面渲染
 * CompletionService将Executor和BlockingQueue的功能融合在一起
 * 可以将Callable任务提交给它来执行，然后使用类型于队列操作的take和poll方法来获得已完成的结果
 *
 * Author liyangyang
 * 2019/1/25
 */
public class Renderer {
    private final ExecutorService executor;
    Renderer(ExecutorService executor){
        this.executor = executor;
    }
    void renderPage(CharSequence source){
        List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
        for(final ImageInfo imageInfo : info){
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });

        }
        renderText(source);
        try {
            for(int t =0,n=info.size();t<n;t++){
                //
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void renderImage(Renderer.ImageData data) {
    }


    private void renderText(CharSequence source) {
    }

    private List<Renderer.ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

    private static class ImageInfo {
        public Renderer.ImageData downloadImage() {
            return null;
        }
    }

    private class ImageData {
    }
}
