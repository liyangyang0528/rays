package com.lyyco.rays.service.concurrent.jcp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用future等待图像下载
 * Author liyangyang
 * 2018/10/20
 */
public class FutureRenderer {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    void renderPage(CharSequence source){
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        //下载图像的任务
        Callable<List<ImageData>> task =
                new Callable<List<ImageData>>() {
                    @Override
                    public List<ImageData> call() throws Exception {
                        List<ImageData> result = new ArrayList<>();
                        for(ImageInfo imageInfo : imageInfos){
                            result.add(imageInfo.downloadImage());
                        }
                        return result;
                    }
                };
        Future<List<ImageData>> future = executor.submit(task);

        renderText(source);
        try{
            List<ImageData> imageData = future.get();
            for(ImageData data : imageData){
                renderImage(data);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            future.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void renderImage(ImageData data) {
    }


    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

    private static class ImageInfo {
        public ImageData downloadImage() {
            return null;
        }
    }

    private class ImageData {
    }
}
