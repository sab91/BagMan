package fr.utt.if26.mytravel;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.utt.if26.mytravel.DAO.PageDAO;

import static org.junit.Assert.*;

/**
 * Created by paf on 10/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class PageDAOTest {
    private Page page1;
    private Page page2;
    private Bdd bd;
    private PageDAO pdao;
    private Cursor c;

    @Before
    public void createDb() {
        Context ct = InstrumentationRegistry.getTargetContext();
        bd = new Bdd(ct);
        pdao = new PageDAO(bd);
        pdao.emptyTable();
        page1 = new Page("title1", "content1", "summary1");
        page2 = new Page("title2", "content2", "summary2");
    }

    @After
    public void closeDb() {
        bd.close();
    }

    @Test
    public void insertRow() throws Exception {
        page1.setId(pdao.insertRow(page1));
        page2.setId(pdao.insertRow(page2));
        assertEquals(1, page1.getId());
        assertEquals(2, page2.getId());
    }

    @Test
    public void deleteRow() throws Exception {
        pdao.deleteRow(page2);
        assertFalse(pdao.getList().contains(page2));
    }

    @Test
    public void getRow() throws Exception {
        assertEquals(page1, pdao.getRow(1));
    }

    @Test
    public void getList() throws Exception {
        assertTrue(pdao.getList().size() > 0);
    }

    @Test
    public void itemToObject() throws Exception {

    }

    @Test
    public void updateRow() throws Exception {

    }

    @Test
    public void deleteAll() throws Exception {
        assertEquals(true, pdao.emptyTable());
    }

}